package BusinessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * The DatabaseConnectivity class is responsible for establishing the connection to the database.
 */
public class DatabaseConnectivity {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee_management";
    private static final String USER = "ostechnix";
    private static final String PASSWORD = "Password123#@!";
    
    /**
     * Returns a connection to the MySQL database.
     *
     * @return A Connection object representing the established database connection.
     * @throws SQLException If a database access error occurs or the URL is null.
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
}
