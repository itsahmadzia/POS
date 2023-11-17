package DBLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/POSdb";
    private static final String USER = "root";
    private static final String PASSWORD = "r13Bne3@7";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
}
