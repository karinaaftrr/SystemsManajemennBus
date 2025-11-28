package Customers;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Customer_ComplaintController implements Initializable {

    @FXML
    private TextArea txtTitle;

    @FXML
    private TextArea txtDetails;

    @FXML
    private Button btnSubmit;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleSubmit(ActionEvent event) {

        String title = txtTitle.getText();
        String details = txtDetails.getText();

        if (title.isEmpty() || details.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Title and Details must be filled!");
            return;
        }

        System.out.println("=== Complaint Submitted ===");
        System.out.println("Title   : " + title);
        System.out.println("Details : " + details);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Your complaint has been submitted!");

        txtTitle.clear();
        txtDetails.clear();
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

    // ================= HELPER METHOD =================
    private void openPage(String fxmlPath, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.out.println("Error loading page: " + fxmlPath);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
