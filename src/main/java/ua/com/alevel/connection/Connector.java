package ua.com.alevel.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String URL = "jdbc:mysql://SG-alevel-3162-master.servers.mongodirector.com:3306/classicmodels";
    private static final String USER = "alevel";
    private static final String PASS = "Qwerty123!";

    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASS);
        return connection;

    }
}
