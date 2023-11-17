package DBLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BusinessLayer.*;

public class ManagerDAO {
    public static boolean authenticate(Manager manager, String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM Manager WHERE username=? AND password=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    manager.setLoggedIn(true);
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
