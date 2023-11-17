package DBLayer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BusinessLayer.*;
import java.util.ArrayList;

public class AdminDAO {
    public static boolean authenticate(Admin admin, String username, String password) {
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
    
public static ArrayList<User> getUsersFromDB() {
    ArrayList<User> users = new ArrayList<>();
    try (Connection connection = DatabaseConnection.getConnection()) {
        // Retrieve data from the Manager table
        String managerQuery = "SELECT username, password FROM Manager";
        try (PreparedStatement managerStatement = connection.prepareStatement(managerQuery)) {
            ResultSet managerResultSet = managerStatement.executeQuery();

            while (managerResultSet.next()) {
                String username = managerResultSet.getString("username");
                String password = managerResultSet.getString("password");
                Role role = new Manager(username,password);
                User user = new User(username, password, role);
                users.add(user);
            }
        }

        // Retrieve data from the SalesAssistant table
        String assistantQuery = "SELECT username, password FROM SalesAssistant";
        try (PreparedStatement assistantStatement = connection.prepareStatement(assistantQuery)) {
            ResultSet assistantResultSet = assistantStatement.executeQuery();

            while (assistantResultSet.next()) {
                String username = assistantResultSet.getString("username");
                String password = assistantResultSet.getString("password");
                Role role = new SalesAssistant(username,password);
                User user = new User(username, password, role);
                users.add(user);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return users;
}



}
