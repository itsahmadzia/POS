package BusinessLayer;
import DBLayer.AdminDAO;
import java.util.ArrayList;

public class Admin {
    private int id;
    private String name;
    private String password;
    private boolean LoggedIn;

    public Admin(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

      public ArrayList<User> loadUsersFromDB() {
        return AdminDAO.getUsersFromDB(); 
    }
    public User addUser(String username, String password, Role role) {
        User user = new User(username, password, role);

        AdminDAO.saveUser(user);

        return user;
    }
    public void viewUsers(ArrayList<User> users) {
        if (users.size() > 0) {
            for (User user : users) {
                System.out.println("Username: " + ", Role: " + user.getRole().log);
            }
        } else {
            System.out.println("No users added");
        }
    }
    
    public boolean isLoggedIn() {
        return LoggedIn;
    }
    
    public void setLoggedIn(boolean b) {
        LoggedIn = b;
    }

    public boolean authenticateFromDB(String inputName, String inputPassword) {
        return AdminDAO.authenticate(this, inputName, inputPassword);
    }
}
