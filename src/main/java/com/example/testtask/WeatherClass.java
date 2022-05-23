package com.example.testtask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class WeatherClass {

    private static String city;

    private static String temperature;

    private static String information;


    public static String getTemperature() {
        return temperature;
    }


    public static String getInformation() {
        return information;
    }

    public static String getCitiesByInput(String query) throws IOException {
        city = query;
        Document doc;
        Element link;
        String urlCity = null;
        String url = "https://pogoda.mail.ru/search/?name=" + query;
        doc = Jsoup.connect(url).get();
        urlCity = doc.location();
        if (urlCity.isEmpty()) {
            Elements names = doc.select("a[href]");
            for (Element e : names) {
                if (city.length() >= 3 && e.text().equals(city)) {
                    urlCity = e.attr("href");
                }
            }
            if (!urlCity.isEmpty())
                urlCity = "https://pogoda.mail.ru" + urlCity;
        }
        return urlCity;
    }

    public static String getTemperatureByCity(String urlCity) throws IOException {
        String url = urlCity;
        String message = null;
        Document page = null;
        try {
            page = Jsoup.parse(new URL(url), 3000);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            message = "URL не найден, неверно указан город";
            System.out.println(message);
        }
        if (page != null) {
            ArrayList<String> infoTemperatureArray = new ArrayList<>();
            Elements temp = page.getElementsByClass("information__content__temperature");
            Elements firstInfo = page.getElementsByClass("information__content__additional information__content__additional_first");
            Elements secondInfo = page.getElementsByClass("information__content__additional information__content__additional_second");
            Elements infoAboutTemp = secondInfo.select("span");
            if (temp.text().isEmpty() || firstInfo.text().isEmpty() || infoTemperatureArray.toString().isEmpty()) {
                message = "Пустые значения";
            }
            if (!infoAboutTemp.isEmpty()) {
                for (Element e : infoAboutTemp) {
                    if (!e.attr("title").isEmpty())
                        infoTemperatureArray.add(e.attr("title"));
                }
            }
            if (!temp.text().isEmpty() && !firstInfo.text().isEmpty() && !infoTemperatureArray.toString().isEmpty()) {
                temperature = temp.text();
                information = firstInfo.text() + ", " + infoTemperatureArray.toString().substring(1, infoTemperatureArray.toString().length() - 1);
            }
        }
        return message;
    }
}
