package Admin;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginadminController {

    @FXML
    private Button btnClose;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    // ✅ CLOSE BUTTON
    @FXML
    private void handleClose(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // ✅ LOGIN BUTTON
    @FXML
    void login(ActionEvent event) {

        String user = username.getText();
        String pass = password.getText();

        System.out.println("Username: " + user);
        System.out.println("Password: " + pass);

        // (Optional) validasi sederhana
        if (user.equals("admin") && pass.equals("123")) {

            try {
                // Panggil Dashboard
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/dashboardd_admin.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Dashboard Admin");
                stage.setScene(new Scene(root, 800, 490));
                stage.show();

                // Tutup window login
                ((Stage)((Node)event.getSource()).getScene().getWindow()).close();

            } catch (IOException e) {
            }

        } else {
            System.out.println("Login gagal!");
        }
    }
}
