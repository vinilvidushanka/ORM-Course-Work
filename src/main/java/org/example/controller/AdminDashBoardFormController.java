package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.config.SessionFactoryConfig;
import org.example.entity.Student;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AdminDashBoardFormController {

    private int studentCount;

    public void initialize() throws IOException {
        initClock();


    }



    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd        HH:mm:ss");
            lblTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    private Label lblProgramsCount;

    @FXML
    private Label lblStudentCount;
    @FXML
    private Label lblTime;
    @FXML
    private AnchorPane root;
    public AnchorPane rootNode;
    @FXML
    private AnchorPane Load;


    private void setStudentCount(int studentCount) {
        lblStudentCount.setText(String.valueOf(studentCount));
    }



    @FXML
    void btnDashboardOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/View/admin_dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Dashboard Form");
    }

    @FXML
    void btnLogOutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/login_form.fxml"));

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) {

    }

    @FXML
    void btnProgramOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/View/program_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/View/student_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        URL resource = getClass().getResource("/View/user_form.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        Load.getChildren().clear();
        Load.getChildren().add(load);
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), Load);
        transition.setFromX(load.getScene().getWidth());
        transition.setToX(0);
        transition.play();
    }
}
