package Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class AvailableBussController implements Initializable {

    public static ObservableList<BusModel> busList = FXCollections.observableArrayList();

    private final int MAX_BUS = 10;

    @FXML private Button btnDashboard;
    @FXML private Button btnAvailableBuses;
    @FXML private Button btnBookingTicket;
    @FXML private Button btnCustomers;
    @FXML private Button btnLogout;

    @FXML private TextField txtBusID;
    @FXML private TextField txtFrom;
    @FXML private TextField txtDestination;
    @FXML private ComboBox<String> cbStatus;
    @FXML private TextField txtPrice;
    @FXML private DatePicker datePicker;
    @FXML private TextField textCapacity;

    @FXML private TableView<BusModel> tableBus;
    @FXML private TableColumn<BusModel, Integer> colBusID;
    @FXML private TableColumn<BusModel, String> colFrom;
    @FXML private TableColumn<BusModel, String> colDestination;
    @FXML private TableColumn<BusModel, String> colStatus;
    @FXML private TableColumn<BusModel, String> colPrice;
    @FXML private TableColumn<BusModel, String> colDate;
    @FXML private TableColumn<BusModel, Integer> colCapacity;

    @FXML private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbStatus.getItems().addAll("Available", "Unavailable", "Maintenance");

        colBusID.setCellValueFactory(data -> data.getValue().busIDProperty().asObject());
        colFrom.setCellValueFactory(data -> data.getValue().fromProperty());
        colDestination.setCellValueFactory(data -> data.getValue().destinationProperty());
        colStatus.setCellValueFactory(data -> data.getValue().statusProperty());
        colPrice.setCellValueFactory(data -> data.getValue().priceProperty());
        colDate.setCellValueFactory(data -> data.getValue().dateProperty());
        colCapacity.setCellValueFactory(data -> data.getValue().capacityProperty().asObject());

        tableBus.setItems(busList);

        tableBus.setOnMouseClicked(event -> {
            BusModel selected = tableBus.getSelectionModel().getSelectedItem();
            if (selected != null) {
                txtBusID.setText(String.valueOf(selected.getBusID()));
                txtFrom.setText(selected.getFrom());
                txtDestination.setText(selected.getDestination());
                cbStatus.setValue(selected.getStatus());
                txtPrice.setText(selected.getPrice());
                LocalDate date = selected.getDateAsLocalDate();
                datePicker.setValue(date);
                textCapacity.setText(String.valueOf(selected.getCapacity()));
            }
        });

        txtSearch.textProperty().addListener((obs, oldV, newV) -> filterTable(newV));
    }

    private boolean isNumeric(String text) {
        return text.matches("\\d+");
    }

    private boolean isAlphabet(String text) {
        return text.matches("[a-zA-Z ]+");
    }

    private boolean validateForm() {

        if (txtBusID.getText().isEmpty()
                || txtFrom.getText().isEmpty()
                || txtDestination.getText().isEmpty()
                || cbStatus.getValue() == null
                || txtPrice.getText().trim().isEmpty()
                || datePicker.getValue() == null
                || textCapacity.getText().trim().isEmpty()) {

            showAlert("Semua field harus diisi!");
            return false;
        }

        if (!isNumeric(txtBusID.getText())) {
            showAlert("Bus ID harus angka!");
            return false;
        }

        if (!isAlphabet(txtFrom.getText()) || !isAlphabet(txtDestination.getText())) {
            showAlert("From dan Destination hanya huruf!");
            return false;
        }

        if (!isNumeric(txtPrice.getText())) {
            showAlert("Harga harus angka!");
            return false;
        }

        if (!isNumeric(textCapacity.getText())) {
            showAlert("Capacity harus angka!");
            return false;
        }

        return true;
    }

    @FXML
    private void addBus(ActionEvent e) {

        if (busList.size() >= MAX_BUS) {
            showAlert("Max bus hanya 10!");
            return;
        }

        if (!validateForm()) return;

        int busId = Integer.parseInt(txtBusID.getText());
        int capacity = Integer.parseInt(textCapacity.getText());

        for (BusModel bus : busList) {
            if (bus.getBusID() == busId) {
                showAlert("Bus ID sudah ada!");
                return;
            }
        }

        BusModel bus = new BusModel(
                busId,
                txtFrom.getText(),
                txtDestination.getText(),
                cbStatus.getValue(),
                txtPrice.getText(),
                datePicker.getValue(),
                capacity
        );

        busList.add(bus);
        resetForm(null);
    }

    @FXML
    private void updateBus(ActionEvent e) {

        BusModel selected = tableBus.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Pilih data dulu!");
            return;
        }

        if (!validateForm()) return;

        int busId = Integer.parseInt(txtBusID.getText());
        int capacity = Integer.parseInt(textCapacity.getText());

        selected.setBusID(busId);
        selected.setFrom(txtFrom.getText());
        selected.setDestination(txtDestination.getText());
        selected.setStatus(cbStatus.getValue());
        selected.setPrice(txtPrice.getText());
        selected.setDate(datePicker.getValue());
        selected.setCapacity(capacity);

        tableBus.refresh();
        resetForm(null);
    }

    @FXML
    private void deleteBus(ActionEvent e) {

        BusModel selected = tableBus.getSelectionModel().getSelectedItem();

        if (selected != null) {
            busList.remove(selected);
        } else {
            showAlert("Pilih bus untuk dihapus!");
        }
    }

    @FXML
    private void resetForm(ActionEvent e) {

        txtBusID.clear();
        txtFrom.clear();
        txtDestination.clear();
        cbStatus.setValue(null);
        txtPrice.clear();
        datePicker.setValue(null);
        textCapacity.clear();
        tableBus.getSelectionModel().clearSelection();
    }

    private void filterTable(String keyword) {

        if (keyword == null || keyword.isEmpty()) {
            tableBus.setItems(busList);
            return;
        }

        String lower = keyword.toLowerCase();
        ObservableList<BusModel> filtered = FXCollections.observableArrayList();

        for (BusModel bus : busList) {
            if (String.valueOf(bus.getBusID()).contains(lower)
                    || bus.getFrom().toLowerCase().contains(lower)
                    || bus.getDestination().toLowerCase().contains(lower)) {

                filtered.add(bus);
            }
        }

        tableBus.setItems(filtered);
    }

    private void openPage(String fxmlName) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlName));
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 490));
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML private void openDashboard() { openPage("/fxml/dashboardd_admin.fxml"); }
    @FXML private void openAvailableBuses() { openPage("/fxml/availablebuss.fxml"); }
    @FXML private void openBookingTicket() { openPage("/fxml/Complaint.fxml"); }
    @FXML private void openCustomers() { openPage("/fxml/Customerr.fxml"); }
    @FXML private void logout() { openPage("/fxml/logincustomer.fxml"); }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
