package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.AdminBO;
import org.example.dto.AdminDto;

import java.io.IOException;

public class LoginFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private final AdminBO adminBO = (AdminBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ADMIN);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
//        boolean isLoginValidated = validateLogin();
//
//        if (!isLoginValidated) {
//            return;
//        }
//
//        AdminDto adminDto = new AdminDto(txtUserName.getText(), txtPassword.getText());
//
//        boolean isAdminExist = adminBO.isAdminExist(adminDto);
//
//        if (!isAdminExist){
//            new Alert(Alert.AlertType.ERROR, "Invalid Username or Password").show();
//            //Highlight Fields
//            txtUserName.getStyleClass().add("mfx-text-field-error");
//            txtPassword.getStyleClass().add("mfx-text-field-error");
//            return;
//        }
//
//        //Clear Fields
//        clearFields();
        //Open the Dashboard
        openDashboard();
    }

    private void openDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    private void clearFields() {
    }

    private boolean validateLogin() {
        return false;
    }

    @FXML
    void linkRegistrationOnAction(ActionEvent event) {

    }

}
