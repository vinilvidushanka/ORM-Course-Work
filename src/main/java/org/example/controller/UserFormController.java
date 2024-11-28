package org.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.StudentDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.tm.StudentTm;
import org.example.tm.UserTm;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserFormController {

    @FXML
    private ComboBox<String> cmbRole;

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
    private TableView<UserTm> tblUser;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);
    ObservableList<User> observableList;
    String ID;

    public void initialize(){
        setCellValueFactory();
        loadAllUsers();

        cmbRole.getItems().addAll("Admin", "Coordinator");
        tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setFieldsWithSelectedRowData(newValue);
            }
        });
    }

    private void setFieldsWithSelectedRowData(UserTm newValue) {
        txtId.setText(newValue.getUserId());
        txtName.setText(newValue.getUserName());
        txtPassword.setText(newValue.getPassword());
        cmbRole.setValue(newValue.getRole());
    }

    private void loadAllUsers() {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();

        try {
            List<UserDto> userList = userBO.getAllUsers();

            for (UserDto userDto : userList) {

                UserTm userTm = new UserTm(
                        userDto.getUserId(),
                        userDto.getUserName(),
                        userDto.getPassword(),
                        userDto.getRole()
                );

                obList.add(userTm);
            }

            tblUser.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading users: " + e.getMessage(), ButtonType.OK).show();
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colUserPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtName.clear();
        txtPassword.clear();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        String id = txtId.getText();
        if (result.orElse(no) == yes) {
            if (!userBO.deleteUser(id)) {
                new Alert(Alert.AlertType.ERROR, "Error!!").show();
            }
        }
//        generateNextUserId();
        clearFields();
        loadAllUsers();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        boolean isValidate = validateUser();

        if (isValidate){
            boolean isSaved = userBO.save(new UserDto(
                    txtId.getText(),
                    txtName.getText(),
                    txtPassword.getText(),
                    (String) cmbRole.getValue()
            ));
            if (isSaved) {
                loadAllUsers();
                clearFields();
                new Alert(Alert.AlertType.CONFIRMATION, "User Saved").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "User UnSaved").show();
            }
        }

    }

    private boolean validateUser() {
        int num=0;
        String userId = txtId.getText();
        boolean isIDValidate= Pattern.matches("(U)[0-9]{3,7}",userId);
        if (!isIDValidate){
            num=1;
            vibrateTextField(txtId);
        }

        String name=txtName.getText();
        boolean isNameValidate= Pattern.matches("[A-z ]{3,}",name);
        if (!isNameValidate){
            num=1;
            vibrateTextField(txtName);
        }

        String password=txtPassword.getText();
        boolean isDurationValidate= Pattern.matches("[A-z 0-9]{3,}",password);
        if (!isDurationValidate){
            num=1;
            vibrateTextField(txtPassword);
        }




        if(num==1){
            num=0;
            return false;
        }else {
            num=0;
            return true;

        }
    }

    private void vibrateTextField(TextField textField) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(0), new KeyValue(textField.translateXProperty(), 0)),
                new KeyFrame(Duration.millis(50), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(100), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(150), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(200), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(250), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(300), new KeyValue(textField.translateXProperty(), 6)),
                new KeyFrame(Duration.millis(350), new KeyValue(textField.translateXProperty(), -6)),
                new KeyFrame(Duration.millis(400), new KeyValue(textField.translateXProperty(), 0))

        );

        textField.setStyle("-fx-border-color: red;");
        timeline.play();

        Timeline timeline1 = new Timeline(
                new KeyFrame(Duration.seconds(3), new KeyValue(textField.styleProperty(), "-fx-border-color: #bde0fe;"))
        );

        timeline1.play();
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtPassword.clear();
        cmbRole.setValue(null);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
        String uid=txtId.getText();
        String name = txtName.getText();
        String password = txtPassword.getText();
        String role = cmbRole.getValue();

        if(userBO.updateUser(new UserDto(uid,name,password,role))){
            new Alert(Alert.AlertType.CONFIRMATION, "Update Successfully!!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "Error!!").show();
        }
        clearFields();
        loadAllUsers();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
