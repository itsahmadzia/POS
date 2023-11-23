package DBLayer;

import BusinessLayer.Admin;
import BusinessLayer.*;

import java.sql.*;
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
    
    public  void insertManager(User manager) throws SQLIntegrityConstraintViolationException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Manager (username, name, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, manager.getUsername());
                preparedStatement.setString(2, manager.getName());
                preparedStatement.setString(3, manager.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLIntegrityConstraintViolationException();

        }
    }

    public  void insertSalesAssistant(User salesAssistant) throws SQLIntegrityConstraintViolationException{
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Operator (username, name, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, salesAssistant.getUsername());
                preparedStatement.setString(2, salesAssistant.getName());
                preparedStatement.setString(3, salesAssistant.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new SQLIntegrityConstraintViolationException();
        }
    }
    
    public List<User> getAllManagers() {
        List<User> managers = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Manager";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");

                    User managerUser = new User(name, username, password);
                    Role managerRole = new Manager();
                    managerUser.setRole(managerRole);

                    managers.add(managerUser);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return managers;
    }
    
    public List<User> getAllSalesAssistants() {
        List<User> salesAssistants = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Operator";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String name = resultSet.getString("name");
                    String password = resultSet.getString("password");

                    User salesAssistantUser = new User(name, username, password);
                    Role salesAssistantRole = new SalesAssistant();
                    salesAssistantUser.setRole(salesAssistantRole);

                    salesAssistants.add(salesAssistantUser);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesAssistants;
    }

    
    public boolean deleteUser(String username, String roleType) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String tableName = getUserTableName(roleType);
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

    public String getUserTableName(String roleType) {
        switch (roleType) {
            case "Manager":
                return "Manager";
            case "Operator":
                return "Operator";
            default:
                throw new IllegalArgumentException("Invalid role type");
        }
    }

}
