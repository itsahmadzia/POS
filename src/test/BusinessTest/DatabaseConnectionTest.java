/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test.BusinessTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    private static  String JDBC_URL = "jdbc:mysql://localhost:3306/test_randomn";
    private static  String USER = "ostechnix";
    private static  String PASSWORD = "Password123#@!";
    
    public static void init(String jdbcUrl, String user, String password) {
        DatabaseConnectionTest.JDBC_URL = jdbcUrl;
        DatabaseConnectionTest.USER = user;
        DatabaseConnectionTest.PASSWORD = password;
    }
      
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/test_randomn", USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found", e);
        }
    }
}
