package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.bo.custom.StudentBO;
import org.example.bo.custom.impl.StudentBOImpl;
import org.example.dto.StudentDto;
import org.example.tm.StudentTm;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class StudentFormController {

    @FXML
    private DatePicker btnDOB;

    @FXML
    private RadioButton btnFemale;

    @FXML
    private RadioButton btnMale;

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
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;
    @FXML
    private ComboBox<String> cmbGender;


    StudentBO studentBO = new StudentBOImpl();

    public void initialize(){
        setCellValueFactory();
        loadAllStudents();

        cmbGender.getItems().addAll("Male", "Female");
        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setFieldsWithSelectedRowData(newValue);
            }
        });
    }

    private void setFieldsWithSelectedRowData(StudentTm selectedStudent) {
        txtId.setText(selectedStudent.getId());
        txtName.setText(selectedStudent.getName());
        txtAddress.setText(selectedStudent.getAddress());
        txtContact.setText(selectedStudent.getContact());
        btnDOB.setValue(LocalDate.parse(selectedStudent.getBirthDay()));
        cmbGender.setValue(selectedStudent.getGender());
    }

    private List<StudentDto> loadAllStudents() {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();

        try {
            List<StudentDto> studentList = studentBO.getAllStudents();

            for (StudentDto studentDto : studentList) {
                String formattedBirthDay = studentDto.getBirthDay().toString();

                // Create StudentTm object with the formatted data
                StudentTm studentTm = new StudentTm(
                        studentDto.getId(),
                        studentDto.getName(),
                        studentDto.getAddress(),
                        studentDto.getContact(),
                        formattedBirthDay,
                        studentDto.getGender()
                );

                // Add each student to the ObservableList
                obList.add(studentTm);
            }

            // Set the ObservableList in the TableView
            tblStudent.setItems(obList);

        } catch (Exception e) {
            // Improved error alert with specific details
            new Alert(Alert.AlertType.ERROR, "Error loading students: " + e.getMessage(), ButtonType.OK).show();
        }
        return null;
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        boolean isSaved = studentBO.save(new StudentDto(
                txtId.getText(),
                txtName.getText(),
                txtAddress.getText(),
                txtContact.getText(),
                btnDOB.getValue(),
                (String) cmbGender.getValue()
        ));
        if (isSaved) {
            loadAllStudents();
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Student Saved").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Student UnSaved").show();
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        btnDOB.setValue(null);
        cmbGender.getSelectionModel().clearSelection();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
