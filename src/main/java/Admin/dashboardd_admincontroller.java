package Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class dashboardd_admincontroller implements Initializable {

    // ================== SIDEBAR BUTTON ==================
    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnAvailableBuses;

    @FXML
    private Button btnBookingTicket;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnLogout;

    // ================== LABEL ==================
    @FXML
    private Label labelAvailableBus;

    @FXML
    private Label labelIncomeToday;

    @FXML
    private Label labelTotalIncome;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        updateAvailableBus();
        updateIncome();
    }

    // ================== DATA DUMMY ==================

    private void updateAvailableBus() {
        int total = getTotalAvailableBus();
        labelAvailableBus.setText(String.valueOf(total));
    }
    private int getTotalAvailableBus() {
        int count = 0;

        for (BusModel bus : AvailableBussController.busList) {
            if (bus.getStatus().equalsIgnoreCase("Available")) {
                count++;
            }
        }

        return count;
    }

    private void updateIncome() {
        long today = getIncomeToday();
        long total = getTotalIncome();

        labelIncomeToday.setText("Rp. " + today);
        labelTotalIncome.setText("Rp. " + total);
    }

    private long getIncomeToday() {
        // dummy: today's income = 350.000
        return 350_000;
    }

    private long getTotalIncome() {
        // dummy: total income = 15.500.000
        return 15_500_000;
    }

    // ================== NAVIGASI ==================

    @FXML
    private void openDashboard(ActionEvent event) throws IOException {
        openPage("/fxml/dashboardd_admin.fxml", event);
    }

    @FXML
    private void openAvailableBuses(ActionEvent event) throws IOException {
        openPage("/fxml/availablebuss.fxml", event);
    }

    @FXML
    private void openComplaint(ActionEvent event) throws IOException {
        openPage("/fxml/Complaint.fxml", event);
    }

    @FXML
    private void openCustomers(ActionEvent event) throws IOException {
        openPage("/fxml/Customerr.fxml", event);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        openPage("/fxml/loginadmin.fxml", event);
    }

    // ================== METHOD OPEN PAGE ==================

    private void openPage(String fxml, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, 800, 490);
        stage.setScene(scene);
        stage.show();
    }
}