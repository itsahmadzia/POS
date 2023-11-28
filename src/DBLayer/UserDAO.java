package DBLayer;
import BusinessLayer.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BusinessLayer.*;

public class UserDAO {
  private Connection connection; 
    public UserDAO() {
        try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
      // Constructor for testing environment
    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    
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
