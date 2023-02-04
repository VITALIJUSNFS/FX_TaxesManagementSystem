package com.example.taxesmanagementsystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {

    public static Connection dbConnection = getDatabaseConnection();
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tarpinisProjektas";

    private static Connection getDatabaseConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (
    SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
