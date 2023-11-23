package DBLayer;
import BusinessLayer.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BusinessLayer.*;

public class UserDAO {
    public boolean authenticate(Role role, String username, String password, String tableName) {
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
}
