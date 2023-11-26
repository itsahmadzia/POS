package BusinessLayer;
import java.util.ArrayList;
import DBLayer.UserDAO;

public class User {
    private String username;
    private String name;
    private String password;
    private Role role; 
    protected boolean loggedIn = false;
    private UserDAO UserDAO;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
    
    public User(String username, String password, Role role) {
        this.name = ""; 
        this.username = username;
        this.password = password;
        this.role = role;
        this.UserDAO = new UserDAO();
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public boolean authenticate(String username, String password, String tableName) {
        return UserDAO.authenticate(username,  password,  tableName);
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(String username, String tablename){
        return UserDAO.getNameFromDB(username,tablename);
    }
}
