package Customers;

import DataBase.Koneksi;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class BookingTicketController implements Initializable {

    // ================= SIDEBAR BUTTON =================
    @FXML private Button btnDashboard;
    @FXML private Button btnComplaint;
    @FXML private Button btnBookingTicket;
    @FXML private Button btnLogout;

    // ================= INPUT ATAS =================
    @FXML private ComboBox<String> busIDCombo;
    @FXML private ComboBox<String> locationCombo;
    @FXML private TextField seatField;

    @FXML private ComboBox<String> genderCombo;
    @FXML private TextField phoneField;
    @FXML private DatePicker customerDate;

    @FXML private Button btnReset;
    @FXML private Button btnSelect;
    @FXML private Button btnPay;
    @FXML private Button btnReceipt;

    // ================= LABEL BAWAH =================
    @FXML private Label selectPhoneLabel;
    @FXML private Label selectGenderLabel;
    @FXML private Label selectDateLabel;
    @FXML private Label selectBusLabel;
    @FXML private Label selectLocationLabel;
    @FXML private Label selectSeatLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        busIDCombo.getItems().addAll("B01", "B02", "B03");
        locationCombo.getItems().addAll(
                "Jakarta - Bandung",
                "Jakarta - Semarang",
                "Jakarta - Surabaya"
        );
        genderCombo.getItems().addAll("Male", "Female");

        resetLabelResult();
    }

    // ================= RESET =================
    @FXML
    private void handleResetTop(ActionEvent event) {

        busIDCombo.setValue(null);
        locationCombo.setValue(null);
        seatField.clear();

        genderCombo.setValue(null);
        phoneField.clear();
        customerDate.setValue(null);

        resetLabelResult();
    }

    // ================= SELECT =================
    @FXML
    private void handleSelect(ActionEvent event) {

        if (phoneField.getText().isEmpty()
                || genderCombo.getValue() == null
                || customerDate.getValue() == null
                || busIDCombo.getValue() == null
                || locationCombo.getValue() == null
                || seatField.getText().isEmpty()) {

            showAlert(Alert.AlertType.WARNING,
                    "Peringatan",
                    "Lengkapi semua data sebelum klik Select!");
            return;
        }

        selectPhoneLabel.setText(phoneField.getText());
        selectGenderLabel.setText(genderCombo.getValue());
        selectDateLabel.setText(customerDate.getValue().toString());

        selectBusLabel.setText(busIDCombo.getValue());
        selectLocationLabel.setText(locationCombo.getValue());
        selectSeatLabel.setText(seatField.getText());
    }

    // ================= PAY =================
    @FXML
    private void handlePay(ActionEvent event) {

        if (selectBusLabel.getText().equals("-")) {
            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Silakan klik Select dulu sebelum bayar!");
            return;
        }

        try {
            String phone = selectPhoneLabel.getText();
            String gender = selectGenderLabel.getText();
            LocalDate localDate = LocalDate.parse(selectDateLabel.getText());
            String busId = selectBusLabel.getText();
            String location = selectLocationLabel.getText();
            String seat = selectSeatLabel.getText();

            Connection conn = Koneksi.getConnection();

            String sql = "INSERT INTO booking_ticket " +
                    "(phone, gender, date, bus_id, location, seat) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, phone);
            pst.setString(2, gender);
            pst.setDate(3, Date.valueOf(localDate));
            pst.setString(4, busId);
            pst.setString(5, location);
            pst.setString(6, seat);

            int result = pst.executeUpdate();

            if (result > 0) {
                showAlert(Alert.AlertType.INFORMATION,
                        "Sukses",
                        "Booking berhasil disimpan!");
                handleResetTop(null);
            } else {
                showAlert(Alert.AlertType.ERROR,
                        "Gagal",
                        "Gagal simpan data!");
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Terjadi error saat menyimpan!");
        }
    }

    // ================= RECEIPT =================
    @FXML
    private void handleReceipt(ActionEvent event) {

        if (selectBusLabel.getText().equals("-")) {
            showAlert(Alert.AlertType.WARNING,
                    "Warning",
                    "Silakan klik Select dulu sebelum buka receipt!");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/fxml/booking_confirmation.fxml")
            );

            Parent root = loader.load();

            // Ambil controller confirmation
            Booking_confirmationController controller = loader.getController();

            controller.setData(
                    selectBusLabel.getText(),
                    selectLocationLabel.getText(),
                    selectSeatLabel.getText(),
                    selectGenderLabel.getText(),
                    selectPhoneLabel.getText(),
                    selectDateLabel.getText()
            );

            Stage stage = new Stage();
            stage.setTitle("Booking Confirmation");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR,
                    "Error",
                    "Gagal membuka halaman confirmation!");
        }
    }

    // ================= LABEL RESET =================
    private void resetLabelResult() {
        selectPhoneLabel.setText("-");
        selectGenderLabel.setText("-");
        selectDateLabel.setText("-");
        selectBusLabel.setText("-");
        selectLocationLabel.setText("-");
        selectSeatLabel.setText("-");
    }

    // ================= NAVIGASI =================
    private void openPage(String fxml, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 490));
            stage.show();
        } catch (IOException e) {
        }
    }

    @FXML
    private void openDashboard(ActionEvent event) {
        openPage("/fxml/DasboardCustomers.fxml", event);
    }

    @FXML
    private void openComplaint(ActionEvent event) {
        openPage("/fxml/Customer_Complaint.fxml", event);
    }

    @FXML
    private void openBookingTicket(ActionEvent event) {
        openPage("/fxml/BookingTicket.fxml", event);
    }

    @FXML
    private void logout(ActionEvent event) {
        openPage("/fxml/logincustomer.fxml", event);
    }
    // ================= ALERT =================
    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
