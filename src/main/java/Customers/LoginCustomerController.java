package Customers;

import DataBase.Koneksi;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginCustomerController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void login(ActionEvent event) {

        String user = username.getText();
        String pass = password.getText();

        if (user.isEmpty() || pass.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Error", "Username dan Password harus diisi!");
            return;
        }

        String sql = "SELECT * FROM regist WHERE username = ? AND password = ?";

        try (Connection conn = Koneksi.getConnection()) {

            if (conn == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Koneksi ke database NULL!");
                System.out.println("❌ Koneksi database null!");
                return;
            }

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, user);
            pst.setString(2, pass);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                showAlert(Alert.AlertType.INFORMATION, "Sukses", "Login berhasil!");

                Parent root = FXMLLoader.load(getClass().getResource("/fxml/DasboardCustomers.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root, 800, 490));
                stage.show();

            } else {
                showAlert(Alert.AlertType.ERROR, "Login Gagal", "Username atau Password salah!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Terjadi error saat login!");
        }
    }

    @FXML
    private void Close(ActionEvent event) {
        Stage stage = (Stage) username.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
