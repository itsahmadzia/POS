package BusinessLayer;

import DBLayer.AdminDAO;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
/**
 * The Admin class represents an administrator in the system.
 * Administrator has the ability to manage users.
 */
public class Admin {
    private String name;
    private String password;
    private boolean LoggedIn;
    /**
    * Data Access Object for authenticating admin users and performing user related operations.
    * This object provides methods to authenticate admin, manage users,
    * and retrieve information about users from the data storage.
    */
    public AdminDAO AdminDAO;
    /**
     * Constructs an Admin object with the specified name and password.
     *
     * @param name     The name of the administrator.
     * @param password The password of the administrator.
     */
    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
        this.AdminDAO = new AdminDAO();
    }
    /**
     * Adds a new user to the system based on the provided user Role.
     *
     * @param name     The name of the user.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param userType The type of the user (e.g., "Manager" or "Sales Assistant").
     * @return true if the user is added successfully, false otherwise.
     * @throws SQLIntegrityConstraintViolationException If there is a constraint violation during database insertion.
     */
    public boolean addUser(String name, String username, String password, String userType) throws SQLIntegrityConstraintViolationException {
        boolean userAdded = false;

        if ("Manager".equals(userType)) {
            User managerUser = new User(name, username, password);
            Role managerRole = new Manager();
            managerUser.setRole(managerRole);
            AdminDAO.insertManager(managerUser);
            userAdded = true;
        } else if ("Sales Assistant".equals(userType)) {
            User salesAssistantUser = new User(name, username, password);
            Role salesAssistantRole = new SalesAssistant();
            salesAssistantUser.setRole(salesAssistantRole);
            AdminDAO.insertSalesAssistant(salesAssistantUser);
            userAdded = true;
        }

        return userAdded;
    }
    
    /**
     * Checks if the administrator is currently logged in.
     *
     * @return true if the administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return LoggedIn;
    }
    
    /**
     * Sets the login status of the administrator.
     *
     * @param status The login status to be set (true for logged in, false for logged out).
     */
    public void setLoggedIn(boolean status) {
        LoggedIn = status;
    }
    /**
     * Authenticates the administrator from the database.
     *
     * @param inputName     The name provided for authentication.
     * @param inputPassword The password provided for authentication.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticateFromDB(String inputName, String inputPassword) {
        return AdminDAO.authenticate(this, inputName, inputPassword);
    }
    /**
     * Retrieves a list of managers from the database.
     *
     * @return List of User objects representing managers.
     */
    public List<User> getUserManagersFromDB() {
        return AdminDAO.getAllManagers();
    }
    
   /**
     * Retrieves a list of sales assistants from the database.
     *
     * @return List of User objects representing sales assistants.
     */
    public List<User> getUserSalesAssistantFromDB() {
        return AdminDAO.getAllSalesAssistants();
    }
   /**
     * Deletes a user from the database based on the username and role type.
     *
     * @param username  The username of the user to be deleted.
     * @param roleType  The role type of the user to be deleted.
     * @return true if the user is successfully deleted, false otherwise.
     */
    public boolean deleteUser(String username ,String roleType) throws SQLIntegrityConstraintViolationException {
        boolean deleted = AdminDAO.deleteUser(username,roleType);
        /*
        if (deleted) {
            System.out.println(" deleted from the database: " + username);
           // updateTableFromDatabase(); 
        } else {
            System.out.println("Failed to delete user " + username);
        }*/
        return deleted;
    }
}
