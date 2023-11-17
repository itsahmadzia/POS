import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String password;
    private Role role;
    protected boolean loggedIn = false;

    public User(int id, String name, String password,Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }
    
    public boolean login(String inputName, String inputPassword) {
        return name.equals(inputName) && password.equals(inputPassword);
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
    
    public void save(ArrayList<User> users) {
        users.add(this);
    }

    public String getName() {
        return name;
    }

    void setLoggedIn(boolean b) {
        loggedIn = b;
    }
       public Role getRole() {
        return role;
    }
}