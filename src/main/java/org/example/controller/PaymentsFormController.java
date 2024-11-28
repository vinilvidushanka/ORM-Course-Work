package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.BOFactory;
import org.example.bo.custom.EnrollmentBO;
import org.example.bo.custom.PaymentBO;
import org.example.dto.PaymentDto;
import org.example.tm.PaymentTm;
import org.example.tm.StudentTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentsFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEnrollId;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private ComboBox<String> cmbEnrollmentId;

    @FXML
    private TextField txtPaymentId;

    ObservableList<PaymentTm> observableList;
    String ID;
    PaymentBO paymentBO = (PaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT);
    EnrollmentBO enrollmentBo = (EnrollmentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ENROLLMENT);
    
    public void initialize() {
        try {
            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            txtDate.setText(today.format(formatter));
            getAll();
            loadEnrollmentIds();

            tblPayment.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    setFieldsWithSelectedRowData(newValue);
                }
            });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        setCellValueFactory();
        generateNextPaymentId();


    }

    private void generateNextPaymentId() {
        String nextId = null;
        try {
            nextId = paymentBO.generateNewPaymentID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtPaymentId.setText(nextId);
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEnrollId.setCellValueFactory(new PropertyValueFactory<>("eid"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    private void setFieldsWithSelectedRowData(PaymentTm selectedPayment) {
        txtPaymentId.setText(selectedPayment.getId());
        cmbEnrollmentId.setValue(selectedPayment.getEid());
        txtAmount.setText(String.valueOf(selectedPayment.getAmount()));
        txtDate.setText(String.valueOf(selectedPayment.getDate()));
    }

    private void loadEnrollmentIds() throws SQLException, ClassNotFoundException {
        List<String> enrollmentIds = enrollmentBo.getAllEnrollmentIds();
        cmbEnrollmentId.getItems().clear();
        cmbEnrollmentId.getItems().addAll(enrollmentIds);
    }

    private void getAll() throws SQLException, ClassNotFoundException {
        observableList = FXCollections.observableArrayList();
        List<PaymentDto> allPayment = paymentBO.getAllPayment();

        for (PaymentDto paymentDTO : allPayment){
            observableList.add(new PaymentTm(paymentDTO.getId(),paymentDTO.getEid(),paymentDTO.getAmount(),paymentDTO.getDate()));
        }
        tblPayment.setItems(observableList);
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtPaymentId.setText("");
        cmbEnrollmentId.setValue(null);
        txtAmount.setText("");
        txtDate.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (result.orElse(no) == yes) {
            if (!paymentBO.deletePayment(ID)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
        clearTextFileds();
        generateNextPaymentId();
        getAll();
        loadEnrollmentIds();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtPaymentId.getText();
        String eid = cmbEnrollmentId.getValue();
        Double amount = Double.valueOf(txtAmount.getText());
        LocalDate date = LocalDate.parse(txtDate.getText());

        if (paymentBO.PaymentIdExists(id)){
            new Alert(Alert.AlertType.ERROR, "Payment ID " + id + " already exists!").show();
            return;
        }

        if(amount > (enrollmentBo.findEnrollmentById(eid).getRemainingfee())){
            new Alert(Alert.AlertType.ERROR, "Payment exceeds the remaining fee. Please enter a valid amount!").show();
            return;
        }

        if (paymentBO.savePayment(new PaymentDto(id,eid,amount,date))) {
            updateremainfee();
            clearTextFileds();
            loadEnrollmentIds();
            generateNextPaymentId();
            getAll();
            new Alert(Alert.AlertType.CONFIRMATION, "Saved!!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
    }

    private void clearTextFileds() {
        txtPaymentId.clear();
        cmbEnrollmentId.getItems().clear();
        txtAmount.clear();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        txtDate.setText(today.format(formatter));
        generateNextPaymentId();
    }

    private void updateremainfee() {
        try {
            String eid = cmbEnrollmentId.getValue();
            double paymentAmount = Double.parseDouble(txtAmount.getText());

            double currentRemainFee = enrollmentBo.getRemainingFeeByEnrollmentId(eid);

            double updatedRemainFee = currentRemainFee - paymentAmount;

            boolean isUpdated = enrollmentBo.updateRemainingFee(eid, updatedRemainFee);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Remaining fee updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update remaining fee!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error updating remaining fee: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String id = txtPaymentId.getText();
        String eid = cmbEnrollmentId.getValue();
        Double amount = Double.valueOf(txtAmount.getText());
        LocalDate date = LocalDate.parse(txtDate.getText());
        Double previousamount = paymentBO.findPaymentById(id).getAmount();

        if(amount > (enrollmentBo.findEnrollmentById(eid).getRemainingfee())){
            new Alert(Alert.AlertType.ERROR, "Payment exceeds the remaining fee. Please enter a valid amount!").show();
            return;
        }
        if(paymentBO.updatePayment(new PaymentDto(id,eid,amount,date))){
            updateremainfees(id,amount,previousamount);
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearTextFileds();
        loadEnrollmentIds();
        getAll();
    }

    private void updateremainfees(String id, Double amount, Double previousamount) {
        try {
            String eid = cmbEnrollmentId.getValue();

            double currentRemainFee = enrollmentBo.getRemainingFeeByEnrollmentId(eid);

            double updatedRemainFee = currentRemainFee - (amount - previousamount);
            System.out.println("Current Remaining Fee: " + currentRemainFee);
            System.out.println("Previous Payment Amount: " + previousamount);
            System.out.println("New Payment Amount: " + amount);

            boolean isUpdated = enrollmentBo.updateRemainingFee(eid, updatedRemainFee);

            if (isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Remaining fee updated successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update remaining fee!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error updating remaining fee: " + e.getMessage()).show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
