package DBLayer;
import BusinessLayer.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BusinessLayer.*;
/**
 * The UserDAO class provides data access methods for user-related operations in the database.
 */
public class UserDAO {
    
    private Connection connection; 
    /**
     * Default constructor for creating a UserDAO instance with its own database connection.
     */
    public UserDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * Constructor for creating a UserDAO instance with a provided database connection.
     *
     * @param connection The database connection to be used by the UserDAO instance.
     */
    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    
    /**
     * Authenticates a user based on the provided username, password, and table name.
     *
     * @param username   The username of the user.
     * @param password   The password of the user.
     * @param tableName  The table name for database operations.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password, String tableName) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        String query = "SELECT * FROM " + tableName + " WHERE username=? AND password=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
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
     * Retrieves the name of a user from the database based on the provided username and table name.
     *
     * @param username   The username of the user.
     * @param tableName  The table name for database operations.
     * @return The name of the user retrieved from the database.
     */
    public String getNameFromDB(String username,String tableName){
    try (Connection connection = DatabaseConnection.getConnection()) {
        String query = "SELECT name FROM " + tableName + " WHERE username=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
 
       if (resultSet.next()) {
                return resultSet.getString("name");
            } else {
                return "Unknown";
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "Unknown";
    }
}
}
