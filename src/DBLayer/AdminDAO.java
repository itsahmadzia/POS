package DBLayer;

import BusinessLayer.Admin;
import BusinessLayer.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The AdminDAO class provides data access methods for interacting with the database
 * related to administrator operations.
 */
public class AdminDAO {
    
    private Connection connection;
    
     /**
     * Constructs an AdminDAO object.
     */
     public AdminDAO() {

    }
    /**
     * Constructs an AdminDAO object with a specified database connection.
     *
     * @param connection The database connection to be used by the AdminDAO.
     */
    public AdminDAO(Connection connection) {
        this.connection = connection;
    }
    
     /**
     * Authenticates the administrator based on the provided username and password.
     *
     * @param admin    The Admin object to be authenticated.
     * @param username The username of the administrator.
     * @param password The password of the administrator.
     * @return true if authentication was successful, false otherwise.
     */
    public boolean authenticate(Admin admin, String username, String password) {
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
    
     /**
     * Inserts a new manager user into the database.
     *
     * @param manager The User object representing the manager to be inserted.
     * @throws SQLIntegrityConstraintViolationException If there is a constraint violation during database insertion.
     */
    public void insertManager(User manager) throws SQLIntegrityConstraintViolationException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (usernameExists(connection, manager.getUsername(), "Manager")) {
                throw new SQLIntegrityConstraintViolationException("Duplicate username");
            }

            String query = "INSERT INTO Manager (username, name, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, manager.getUsername());
                preparedStatement.setString(2, manager.getName());
                preparedStatement.setString(3, manager.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error: Unable to insert Manager. Please try again later.");
        }
    }
    
    /**
    * Checks if a username already exists in a specific table within the database.
    *
    * @param connection The database connection.
    * @param username   The username to check for existence.
    * @param tableName  The name of the table in which to check for the username.
    * @return true if the username exists in the specified table, false otherwise.
    * @throws SQLException If a database access error occurs or the SQL execution fails.
    */
    private boolean usernameExists(Connection connection, String username, String tableName) throws SQLException {
        String query = "SELECT COUNT(*) FROM " + tableName + " WHERE username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                int count = resultSet.getInt(1);
                return count > 0;
            }
        }
    }

    /**
     * Inserts a new sales assistant user into the database.
     *
     * @param salesAssistant The User object representing the sales assistant to be inserted.
     * @throws SQLIntegrityConstraintViolationException If there is a constraint violation during database insertion.
     */
    
    public void insertSalesAssistant(User salesAssistant) throws SQLIntegrityConstraintViolationException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (usernameExists(connection, salesAssistant.getUsername(),"Operator")) {
                throw new SQLIntegrityConstraintViolationException("Duplicate username");
            }

            String query = "INSERT INTO Operator (username, name, password) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, salesAssistant.getUsername());
                preparedStatement.setString(2, salesAssistant.getName());
                preparedStatement.setString(3, salesAssistant.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            // Handle other SQL exceptions
            e.printStackTrace();
            throw new RuntimeException("Error: Unable to insert Sales Assistant. Please try again later.");
        }
    }

     /**
     * Retrieves a list of all managers from the database.
     *
     * @return List of User objects representing managers.
     */
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
    /**
     * Retrieves a list of all sales assistants from the database.
     *
     * @return List of User objects representing sales assistants.
     */
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

    /**
     * Deletes a user from the database based on the username and role.
     *
     * @param username  The username of the user to be deleted.
     * @param roleType  The role of the user to be deleted.
     * @return true if the user is successfully deleted, false otherwise.
     * @throws SQLIntegrityConstraintViolationException If there is a violation of integrity constraints.
     */
    public boolean deleteUser(String username, String roleType) throws SQLIntegrityConstraintViolationException {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String tableName = getUserTableName(roleType);
            String query = "DELETE FROM " + tableName + " WHERE username=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, username);
                int affectedRows = preparedStatement.executeUpdate();

                return affectedRows > 0;
            }
        } catch (SQLException e) {
            if(e instanceof SQLIntegrityConstraintViolationException){
                throw new SQLIntegrityConstraintViolationException();
            }
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gets the table name associated with a user role.
     *
     * @param roleType The role type of the user.
     * @return The table name associated with the user role type.
     * @throws IllegalArgumentException If an invalid role type is provided.
     */
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
