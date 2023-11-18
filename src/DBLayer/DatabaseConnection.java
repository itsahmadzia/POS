package DBLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//key==== 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
public class DatabaseConnection {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/randomn";
    private static final String USER = "ostechnix";
    private static final String PASSWORD = "Password123#@!";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
}
