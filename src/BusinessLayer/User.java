package BusinessLayer;
import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private Role role; 
    protected boolean loggedIn = false;

    public User(String username, String password, Role role) {
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

    public void setLoggedIn(boolean b) {
        loggedIn = b;
    }

    public Role getRole() {
        return role;
    }
}
