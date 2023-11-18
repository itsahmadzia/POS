package BusinessLayer;
import java.util.ArrayList;

public class User {
    private String username;
    private String name;
    private String password;
    //private Role role; 
    private String role;
    protected boolean loggedIn = false;

    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean login(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void save(ArrayList<User> users) {
        users.add(this);
    }

    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public void setLoggedIn(boolean b) {
        loggedIn = b;
    }

    public String getRole() {
        return role;
    }
}
