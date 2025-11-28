package Customers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import DataBase.RegistDAO;

public class Register_CustomerController {

    @FXML
    private TextField txtFullName;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void handleRegister(ActionEvent event) {

        String fullName = txtFullName.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        // =================== VALIDASI ===================

        if (fullName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Semua field wajib diisi!");
            return;
        }

        if (!fullName.matches("[a-zA-Z ]+")) {
            showAlert("Error", "Full Name hanya boleh berisi huruf!");
            return;
        }

        if (!password.matches("\\d{6}")) {
            showAlert("Error", "Password harus berupa 6 digit angka!");
            return;
        }

        // =================== INSERT DATABASE ===================

        boolean success = RegistDAO.insertRegist(fullName, username, password);

        if (success) {
            showAlert("Sukses", "Registrasi berhasil! Silakan login.");

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/logincustomer.fxml"));

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Login Customer");
                stage.setScene(new Scene(root, 800, 490));
                stage.show();

            } catch (IOException e) {
                System.out.println("Gagal pindah ke halaman Login: " + e.getMessage());
            }

        } else {
            showAlert("Error", "Username sudah digunakan atau gagal menyimpan data!");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/UserrChoice.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Pilih Akun");
            stage.setScene(new Scene(root, 800, 490));
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.out.println("Gagal kembali ke halaman Choice: " + e.getMessage());
        }
    }

    // =================== ALERT ===================

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
