package com.example.testtask;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class RateExchange {
    private static final String DB_URL = "jdbc:h2:~/IdeaProjects/TestTask/db/RateExchange";
    private static final String DB_Driver = "org.h2.Driver";
    private static final String USER = "sa";
    private static final String PASS = "";

    private static  Map<String, String> values = new HashMap<>();

    public static void DbConnectionNewTable(){
        Statement stmt = null;
        Connection connection = null;
        try{
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Выполнено");

            stmt = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS RATE_EXCHANGE (" +
                    "id  INT NOT NULL, " +
                    "title VARCHAR(50) NOT NULL, " +
                    "currency VARCHAR(20) NOT NULL, " +
                    "submisson_date DATE" +
                    ")";
            stmt.executeUpdate(sql);
            System.out.println("Created table in given database...");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC драйвер для СУБД не найден");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка к подключению SQL");
        } finally {
            //finally block used to close resources
            try {
                if (stmt!=null)
                    connection.close();
            } catch (SQLException se) {
            } // do nothing
            try {
                if (connection!= null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
        }

        public static void DbConnectionDeleteRecords(){
            Statement stmt = null;
            Connection connection = null;
            try{
                Class.forName(DB_Driver);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Выполнено");

                stmt = connection.createStatement();

                String sql = "DELETE FROM RATE_EXCHANGE";

                stmt.executeUpdate(sql);
                System.out.println("deleted");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("JDBC драйвер для СУБД не найден");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ошибка к подключению SQL");
            }finally {
                //finally block used to close resources
                try {
                    if (stmt!=null)
                        connection.close();
                } catch (SQLException se) {
                } // do nothing
                try {
                    if (connection!= null)
                        connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                } // end finally try
            } // end try
            System.out.println("Goodbye!");
        }

        public static void DbConnectionInsertRecords(int id, String title, String currency, String date){
            Statement stmt = null;
            Connection connection = null;
            try{
                Class.forName(DB_Driver);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Выполнено");

                stmt = connection.createStatement();

                String sql = "INSERT INTO RATE_EXCHANGE VALUES(" + id + ", '" + title + "', '" + currency +
                        "', '" + date + "');";
                stmt.executeUpdate(sql);
                System.out.println("inserted");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("JDBC драйвер для СУБД не найден");
            } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("Ошибка к подключению SQL");
            }finally {
                //finally block used to close resources
                try {
                    if (stmt!=null)
                        connection.close();
                } catch (SQLException se) {
                } // do nothing
                try {
                    if (connection!= null)
                        connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                } // end finally try
            } // end try
            System.out.println("Goodbye!");
        }

        public static Map<String,String> DbConnectionSelectValues(){
            Statement stmt = null;
            Connection connection = null;
            try{
                Class.forName(DB_Driver);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);

                stmt = connection.createStatement();

                String sql = "SELECT * FROM RATE_EXCHANGE ORDER BY ID";
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()){
                    String date = rs.getString("submisson_date");
                    String date_curr = RateClass.getDate();
                    if (date.equals(date_curr))
                        values.put(rs.getString("TITLE"), rs.getString("CURRENCY"));
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("JDBC драйвер для СУБД не найден");
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Ошибка к подключению SQL");
            }finally {
                //finally block used to close resources
                try {
                    if (stmt!=null)
                        connection.close();
                } catch (SQLException se) {
                } // do nothing
                try {
                    if (connection!= null)
                        connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                } // end finally try
            } // end try
            if (!values.isEmpty())
                return values;
            else
                return null;
        }
}

