package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class UserFormController {

    @FXML
    private ComboBox<?> cmbRole;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableColumn<?, ?> colUserPassword;

    @FXML
    private TableColumn<?, ?> colUserRole;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblUser;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
