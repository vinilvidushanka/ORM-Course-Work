package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.bo.custom.ProgramsBO;
import org.example.bo.custom.impl.ProgramsBOImpl;
import org.example.dto.ProgramsDto;
import org.example.tm.ProgramsTm;

import java.util.List;

public class ProgramFormController {

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<ProgramsTm> tblProgram;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    ProgramsBO programsBO=new ProgramsBOImpl();

    public void initialize(){
        setCellValueFactory();
        loadAllPrograms();

        tblProgram.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setFieldsWithSelectedRowData(newValue);
            }
        });
    }

    private void setFieldsWithSelectedRowData(ProgramsTm newValue) {
        txtId.setText(newValue.getId());
        txtName.setText(newValue.getName());
        txtDuration.setText(newValue.getDuration());
        txtFee.setText(String.valueOf(newValue.getFee()));
    }

    private void loadAllPrograms() {
        ObservableList<ProgramsTm> obList = FXCollections.observableArrayList();

        try {
            List<ProgramsDto> programList = programsBO.getAllPrograms();

            for (ProgramsDto programsDto : programList) {
                ProgramsTm programsTm = new ProgramsTm(
                        programsDto.getId(),
                        programsDto.getName(),
                        programsDto.getDuration(),
                        programsDto.getFee()
                );

                obList.add(programsTm);
            }

            tblProgram.setItems(obList);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error loading programs: " + e.getMessage(), ButtonType.OK).show();
        }
    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        boolean isSaved = programsBO.save(new ProgramsDto(
                txtId.getText(),
                txtName.getText(),
                txtDuration.getText(),
                txtFee.getText()
        ));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

}
