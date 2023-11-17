package DBLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BusinessLayer.*;

public class SalesAssistantDAO {
    public static boolean authenticate(SalesAssistant salesAssistant, String username, String password) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM SalesAssistant WHERE username=? AND password=?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, username);
                statement.setString(2, password);
                ResultSet resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    salesAssistant.setLoggedIn(true);
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
