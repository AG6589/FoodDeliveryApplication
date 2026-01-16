package com.foodapp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL =
            "jdbc:mysql://localhost:3306/food_delivery_app";
    private static final String USER = "root";
    private static final String PASSWORD = "Aman@gupta6589"; // change

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
