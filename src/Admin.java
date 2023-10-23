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

    public User addUser(ArrayList<User> users, String username, String password, String Role) {
        User user = new User(users.size() + 1, username, password,Role);
        users.add(user);
        return user;
    }
    
    public void viewUsers(ArrayList<User> users) {
        if (users.size() > 0) {
            for (User user : users) {
                System.out.println("Username: " + user.getName() + ", Role: " + user.getRole());
            }
        } else {
            System.out.println("No users added");
        }
    }

    public boolean authenticate( String inputName, String inputPassword){
        return name.equals(inputName) && password.equals(inputPassword);
    }
    
    public boolean isLoggedIn() {
        return LoggedIn;
    }
    
    public void setLoggedIn(boolean b) {
        LoggedIn = b;
    }

}

