package DBLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//key==== 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
/**
 * The DatabaseConnection class provides a utility for establishing a connection to a MySQL database.
 * It follows the singleton pattern to ensure that only one instance of the Connection is created.
 */
public class DatabaseConnection {
   // private static final String JDBC_URL = "jdbc:mysql://localhost:3306/randomn";//for running project
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_randomn";//for running the test cases
    private static final String USER = "ostechnix";
    private static final String PASSWORD = "Password123#@!";

    /**
     * Gets a connection to the database.
     *
     * @return The Connection object representing the database connection.
     * @throws SQLException If there is an issue establishing the database connection.
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
