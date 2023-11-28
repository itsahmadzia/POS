/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package DBTest;

import BusinessLayer.Role;
import BusinessLayer.User;
import BusinessTest.DatabaseConnectionTest;
import DBLayer.DatabaseConnection;
import DBLayer.UserDAO;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DBLayer.UserDAO;


import DBLayer.UserDAO;

public class UserDAOTest {

    private Connection connection;
    private UserDAO userDAO;
    private static final String TESTING_JDBC_URL = "jdbc:mysql://localhost:3306/test_randomn";
    private static final String TESTING_USER = "ostechnix";
    private static final String TESTING_PASSWORD = "Password123#@!";

    @Before
   
    public void setUp() {
        DatabaseConnectionTest.init(TESTING_JDBC_URL, TESTING_USER, TESTING_PASSWORD);

        try {
            connection = DatabaseConnectionTest.getConnection();
            userDAO = new UserDAO();

            clearTestData();
            
            insertTestData("Manager", "test_manager", "test_manager_password", "Test Manager");

        } catch (SQLException e) {
            throw new RuntimeException("Failed to get a connection to the testing database", e);
        }
    }

    private void clearTestData() {
    try (Connection connection = DatabaseConnection.getConnection()) {
        String deleteQuery = "DELETE FROM Manager";
        try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Test
    public void testAuthenticate() {
        assertTrue(userDAO.authenticate("test_manager", "test_manager_password", "Manager"));
        assertFalse(userDAO.authenticate("invalid_user", "invalid_password", "Manager"));
    }

    @Test
    public void testGetNameFromDB() {
        String managerName = userDAO.getNameFromDB("test_manager", "Manager");
        assertEquals("Test Manager", managerName);

        String unknownName = userDAO.getNameFromDB("non_existing_user", "Manager");
        assertEquals("Unknown", unknownName);
    }


       private void insertTestData(String tableName, String username, String password, String name) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String insertQuery = "INSERT INTO " + tableName + " (username, password, name) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, name);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
