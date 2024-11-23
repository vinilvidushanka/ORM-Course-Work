package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.StudentDto;
import org.example.dto.UserDto;
import org.example.entity.User;
import org.example.tm.StudentTm;
import org.example.tm.UserTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, IOException, ClassNotFoundException {
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

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtPassword.clear();
//        cmbRole.clear();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
