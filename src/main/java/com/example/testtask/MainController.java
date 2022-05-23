package com.example.testtask;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    private Stage stage;
    private Scene scene;

    protected void ChangeScene(String name, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(name));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void OnButtonTask1Click(ActionEvent event) throws IOException {
        ChangeScene("Task1.fxml", event);

    }

    @FXML
    public void OnButtonTask2Click(ActionEvent event) throws IOException {
        ChangeScene("Task2.fxml", event);
    }


    @FXML
    public void OnButtonBackToMenuClick(ActionEvent event) throws IOException {
        ChangeScene("hello-view.fxml", event);
    }


}