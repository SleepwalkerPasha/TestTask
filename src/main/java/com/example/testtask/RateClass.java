package com.example.testtask;

import javafx.event.ActionEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RateClass {
   private static String jsonString = "https://www.cbr-xml-daily.ru/latest.js";

   private static Map<String, String> map = new HashMap<>();
   private static String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

   public static String getJsonString() {
      return jsonString;
   }

   public static void setJsonString(String jsonString) {
      RateClass.jsonString = jsonString;
   }

   public static Map<String, String> getMap() {
      return map;
   }

   public static void setMap(Map<String, String> map) {
      RateClass.map = map;
   }

   public static String getDate() {
      return date;
   }

   public static void setDate(String date) {
      RateClass.date = date;
   }

   public static void RateParser() throws IOException {
      URL url = new URL(jsonString);
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
      String inputLine;
      String[] strings;
      Double value;
      while ((inputLine = in.readLine()) != null){

         if(inputLine.contains("date")) {
            strings = inputLine.split(": ");
            date = strings[1].substring(1, strings[1].length() - 2);
            map = new HashMap<>();
         }
         if(inputLine.contains("EUR")){
            strings = inputLine.split(": ");
            value = Double.valueOf(strings[1].substring(0, strings[1].length() - 1));
            value =  1 / value;
            map.put("EUR", String.format("%.6f", value));
         }
         if(inputLine.contains("USD")){
            strings = inputLine.split(": ");
            value = Double.valueOf(strings[1].substring(0, strings[1].length() - 1));
            value = 1 / value;
            map.put("USD", String.format("%.6f", value));
         }
         if(inputLine.contains("CHF")){
            strings = inputLine.split(": ");
            value = Double.valueOf(strings[1].substring(0, strings[1].length() - 1));
            value = 1 / value;
            map.put("CHF", String.format("%.6f", value));
         }
      }
   }

   public static String getCurrentCurrency(String key) {
      //update db
      map = RateExchange.DbConnectionSelectValues();
      if (map == null) {
         System.out.println("База пустая, нет курса по текущей дате");
         try {
            RateParser();
         } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка открытия URL");
         }

      RateExchange.DbConnectionInsertRecords(1, "EUR", map.get("EUR"), date);
      RateExchange.DbConnectionInsertRecords(2, "USD", map.get("USD"), date);
      RateExchange.DbConnectionInsertRecords(3, "CHF", map.get("CHF"), date);
      }
      return map.get(key);
   }
}
