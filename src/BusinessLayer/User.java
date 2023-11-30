package BusinessLayer;
import java.util.ArrayList;
import DBLayer.UserDAO;
/**
 * The User class represents a user in the system with properties like username,
 * name, password, and role. It provides methods for user authentication and login status.
 */
public class User {
    private String username;
    private String name;
    private String password;
    private Role role; 
    protected boolean loggedIn = false;
    private UserDAO UserDAO;
    /**
     * Constructs a User with the specified name, username, and password.
     *
     * @param name     The name of the user.
     * @param username The username for authentication.
     * @param password The password for authentication.
     */
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }
    /**
     * Constructs a User with the specified username, password, and role.
     *
     * @param username The username for authentication.
     * @param password The password for authentication.
     * @param role     The role assigned to the user.
     */
    public User(String username, String password, Role role) {
        this.name = ""; 
        this.username = username;
        this.password = password;
        this.role = role;
        this.UserDAO = new UserDAO();
    }
    /**
     * Performs user login authentication.
     *
     * @param inputUsername The input username for authentication.
     * @param inputPassword The input password for authentication.
     * @return True if the provided credentials are valid, otherwise false.
     */
    public boolean login(String inputUsername, String inputPassword) {
        return username.equals(inputUsername) && password.equals(inputPassword);
    }
    /**
     * Checks if the user is currently logged in.
     *
     * @return True if the user is logged in, otherwise false.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
    /**
     * Saves the user to the provided list of users.
     *
     * @param users The list of users to which the current user will be added.
     */
    public void save(ArrayList<User> users) {
        users.add(this);
    }
    
    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }
    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the login status of the user.
     *
     * @param b True if the user is logged in, otherwise false.
     */
    public void setLoggedIn(boolean b) {
        loggedIn = b;
    }
    /**
     * Gets the role assigned to the user.
     *
     * @return The role of the user.
     */
    public Role getRole() {
        return role;
    }
    /**
     * Sets the role for the user.
     *
     * @param role The role to be assigned to the user.
     */
    public void setRole(Role role) {
        this.role = role;
    }
    /**
     * Authenticates the user using the provided username, password, and table name.
     *
     * @param username   The username for authentication.
     * @param password   The password for authentication.
     * @param tableName  The table name for database operations.
     * @return True if the authentication is successful, otherwise false.
     */
    public boolean authenticate(String username, String password, String tableName) {
        return UserDAO.authenticate(username,  password,  tableName);
    }
    /**
     * Sets the name of the user.
     *
     * @param name The name to be set for the user.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Gets the name of the user from the database using the provided username and table name.
     *
     * @param username   The username for retrieving the name.
     * @param tablename  The table name for database operations.
     * @return The name of the user retrieved from the database.
     */
    public String getName(String username, String tablename){
        return UserDAO.getNameFromDB(username,tablename);
    }

    
    /**
     * Returns a string representation of the User object.
     *
     * @return A string representation of the User.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
