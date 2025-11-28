package Customers;

import Admin.AvailableBussController;
import Admin.BusModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class DasboardCustomersController implements Initializable {

    // ================= COMPONENT FXML =================
    @FXML private Button btnDashboard;
    @FXML private Button btnAvailableBuses;
    @FXML private Button btnBookingTicket;
    @FXML private Button btnLogout;

    @FXML private Label labelAvailableBus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateAvailableBus();
    }

    // ================== UPDATE JUMLAH BUS TERSEDIA ==================
    private void updateAvailableBus() {
        int total = 0;

        for (BusModel bus : AvailableBussController.busList) {
            if (bus.getStatus().equalsIgnoreCase("Available")) {
                total++;
            }
        }

        labelAvailableBus.setText(String.valueOf(total));
    }

    // ================== NAVIGASI MENU ==================

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
    

    // ================== METHOD PINDAH PAGE ==================

    private void openPage(String fxml, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 490));
            stage.show();
        } catch (IOException e) {
            System.out.println("Gagal buka page: " + fxml);
        }
    }
}
