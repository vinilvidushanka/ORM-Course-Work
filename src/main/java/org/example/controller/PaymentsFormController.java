package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PaymentsFormController {

    @FXML
    private DatePicker btnDate;

    @FXML
    private ComboBox<?> cmbDescription;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDOB;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colProgram;

    @FXML
    private TableColumn<?, ?> colRegDate;

    @FXML
    private TableView<?> tblStudent;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtPayment;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtProgram;

    @FXML
    private TextField txtRemainingAmount;

    @FXML
    private TextField txtStName;

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
