package BusinessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private Connection connection;
    private String url;
    private String username;
    private String password;

    public DatabaseConnector() {
        this.url ="jdbc:mysql://localhost:3306/assignment3";
        this.username = "ostechnix";
        this.password = "Password123#@!";
    }

    public Connection connect() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connected to the database.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Failed to connect to the database.");
        }
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from the database.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Failed to disconnect from the database.");
            }
        }
    }
}