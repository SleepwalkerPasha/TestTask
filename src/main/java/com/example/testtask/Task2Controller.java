package com.example.testtask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task2Controller {
    @FXML
    private TextField city;
    @FXML
    private TextField date;
    @FXML
    private Text temp;

    @FXML
    private Text infoTemp;
    @FXML
    private Button button;

    private String cityValue;
    @FXML
    public void OnCityAction(ActionEvent event){
        cityValue = city.getText();
        date.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        try{
            if (WeatherClass.getTemperatureByCity(WeatherClass.getCitiesByInput(cityValue)) == null){
                temp.setText(WeatherClass.getTemperature());
                infoTemp.setText(WeatherClass.getInformation());
            }else {
                    temp.setText("Ссылка на прогноз погоды не найдена");
                    infoTemp.setText("Неверно указан город");
            }
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Неверный url");
        }
    }
    @FXML
    public void OnButtonBackToMenuClick(ActionEvent event) throws IOException {
        MainController mainController = new MainController();
        mainController.ChangeScene("hello-view.fxml", event);
    }
}
