package com.mycompany.systemmanagemenbus;

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
import javafx.stage.Stage;

public class UserrChoiceController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void loginAdmin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginadmin.fxml"));

            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Login Admin");
            stage.setScene(new Scene(root, 800, 490));
            stage.show();

        } catch (IOException e) {
            System.out.println("Gagal membuka halaman Login Admin");
        }
    }

    @FXML
    private void ChoiceAccount(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/ChoiceAccount.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Pilih Akun");
            stage.setScene(new Scene(root, 800, 490));
            stage.show();

        } catch (IOException e) {
            System.out.println("Gagal membuka halaman Pilih Akun");
        }
       }
}
