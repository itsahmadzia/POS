package DBLayer;

import BusinessLayer.Admin;
import BusinessLayer.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    
    public  boolean authenticate(Admin admin, String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Admin WHERE username=? AND password=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    admin.setLoggedIn(true);
                    return true; 
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public  void insertManager(User manager) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        String query = "INSERT INTO Manager (username, name, password) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, manager.getUsername());
            preparedStatement.setString(2, manager.getName());
            preparedStatement.setString(3, manager.getPassword());
            preparedStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public  void insertSalesAssistant(User salesAssistant) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO SalesAssistant (username, name, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, salesAssistant.getUsername());
                preparedStatement.setString(2, salesAssistant.getName());
                preparedStatement.setString(3, salesAssistant.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public  List<User> getAllManagers() {
        List<User> managers = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Manager";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");

                    User manager = new User(name, username, password, "Manager");
                    managers.add(manager);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managers;
    }

    public  List<User> getAllSalesAssistants() {
        List<User> salesAssistants = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM SalesAssistant";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");

                    User salesAssistant = new User(name, username, password, "Sales Assistant");
                    salesAssistants.add(salesAssistant);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesAssistants;
    }
    
    public boolean deleteUser(String username, String userType) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String tableName = getUserTableName(userType);
            String query = "DELETE FROM " + tableName + " WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                int affectedRows = preparedStatement.executeUpdate();

                return affectedRows > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private String getUserTableName(String userType) {
        if ("Manager".equals(userType)) {
            return "Manager";
        } else if ("Sales Assistant".equals(userType)) {
            return "SalesAssistant";
        }
        return null;
    }
}
