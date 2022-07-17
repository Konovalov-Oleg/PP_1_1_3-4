package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {
    private static final String DB_USER_NAME = "Konovalov";
    private static final String DB_PASSWORD = "Konovalov";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/mysql";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USER_NAME, DB_PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
