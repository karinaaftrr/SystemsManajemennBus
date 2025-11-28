package Admin;

import DataBase.Koneksi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CustomerrController implements Initializable {

    @FXML private Button btnDashboard;
    @FXML private Button btnAvailableBuses;
    @FXML private Button btnBookingTicket;
    @FXML private Button btnCustomers;
    @FXML private Button btnLogout;

    @FXML private TableView<CustomerModel> tableCustomer;
    @FXML private TableColumn<CustomerModel, String> colId;
    @FXML private TableColumn<CustomerModel, String> colSeat;
    @FXML private TableColumn<CustomerModel, String> colName;
    @FXML private TableColumn<CustomerModel, String> colGender;
    @FXML private TableColumn<CustomerModel, String> colPhone;
    @FXML private TableColumn<CustomerModel, String> colBusID;
    @FXML private TableColumn<CustomerModel, String> colLocation;
    @FXML private TableColumn<CustomerModel, String> colDate;

    @FXML private TextField txtSearch;
    @FXML private ComboBox<String> cbSort;

    private final ObservableList<CustomerModel> customerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cbSort.getItems().addAll("Nama", "Bus ID", "Tanggal", "Seat");
        cbSort.setOnAction(e -> loadCustomerData());

        colId.setCellValueFactory(data -> data.getValue().idProperty());
        colSeat.setCellValueFactory(data -> data.getValue().seatProperty());
        colName.setCellValueFactory(data -> data.getValue().nameProperty());
        colGender.setCellValueFactory(data -> data.getValue().genderProperty());
        colPhone.setCellValueFactory(data -> data.getValue().phoneProperty());
        colBusID.setCellValueFactory(data -> data.getValue().busIdProperty());
        colLocation.setCellValueFactory(data -> data.getValue().locationProperty());
        colDate.setCellValueFactory(data -> data.getValue().dateProperty());

        tableCustomer.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        txtSearch.textProperty().addListener((obs, oldV, newV) -> searchCustomer(newV));

        loadCustomerData();
    }

    private void loadCustomerData() {

        customerList.clear();

        String orderBy = "";

        if (cbSort.getValue() != null) {
            switch (cbSort.getValue()) {
                case "Nama": orderBy = " ORDER BY name ASC"; break;
                case "Bus ID": orderBy = " ORDER BY bus_id ASC"; break;
                case "Tanggal": orderBy = " ORDER BY date ASC"; break;
                case "Seat": orderBy = " ORDER BY seat ASC"; break;
            }
        }

        String sql = "SELECT * FROM booking_ticket" + orderBy;

        try {
            Connection conn = Koneksi.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                CustomerModel customer = new CustomerModel(
                        rs.getString("id_customer"),
                        rs.getString("seat"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("phone"),
                        rs.getString("bus_id"),
                        rs.getString("location"),
                        rs.getString("date")
                );

                customerList.add(customer);
            }

            tableCustomer.setItems(customerList);

        } catch (SQLException e) {
            System.out.println("Error load customer: " + e.getMessage());
        }
    }

    private void searchCustomer(String keyword) {

        if (keyword.isEmpty()) {
            tableCustomer.setItems(customerList);
            return;
        }

        ObservableList<CustomerModel> filtered = FXCollections.observableArrayList();

        for (CustomerModel customer : customerList) {
            if (customer.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                customer.getBusId().toLowerCase().contains(keyword.toLowerCase()) ||
                customer.getSeat().toLowerCase().contains(keyword.toLowerCase())) {
                filtered.add(customer);
            }
        }

        tableCustomer.setItems(filtered);
    }

    private void openPage(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) btnDashboard.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 490));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error nav: " + e.getMessage());
        }
    }

    @FXML private void openDashboard() { openPage("/fxml/dashboardd_admin.fxml"); }
    @FXML private void openAvailableBuses() { openPage("/fxml/availablebuss.fxml"); }
    @FXML private void openBookingTicket() { openPage("/fxml/BookingTicket.fxml"); }
    @FXML private void openCustomers() { openPage("/fxml/Customerr.fxml"); }
    @FXML private void logout() { openPage("/fxml/loginadmin.fxml"); }
}
