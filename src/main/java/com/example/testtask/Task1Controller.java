package com.example.testtask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Task1Controller implements Initializable {
    @FXML
    private ChoiceBox<String> CurrencyBox;

    @FXML
    private TextField CurrencyField;

    private String[] currency = {"USD", "EUR", "CHF"};

    @FXML
    public void OnButtonBackToMenuClick(ActionEvent event) throws IOException {
        MainController mainController = new MainController();
        mainController.ChangeScene("hello-view.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CurrencyBox.getItems().addAll(currency);
        CurrencyBox.setOnAction(this::getCurrency);
    }

    public void getCurrency(ActionEvent event){
        CurrencyField.setText(RateClass.getCurrentCurrency(CurrencyBox.getValue()));
    }
}
