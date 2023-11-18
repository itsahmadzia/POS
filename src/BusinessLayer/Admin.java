package BusinessLayer;
import DBLayer.AdminDAO;
import java.util.ArrayList;
import java.util.List;

public class Admin {
    private String name;
    private String password;
    private boolean LoggedIn;
    private AdminDAO AdminDAO;

    public Admin(String name, String password) {
        this.name = name;
        this.password = password;
        this.AdminDAO = new AdminDAO();
    }

    public void addUser(String name, String username, String password, String userType) {
        
        if ("Manager".equals(userType)) {
            User manager = new User(name,username,password,userType);
            AdminDAO.insertManager(manager);
            
        } else if ("Sales Assistant".equals(userType)) {
            User salesAssistant = new User(name,username,password,userType);
            AdminDAO.insertSalesAssistant(salesAssistant);
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
    
    public List<User> getUserManagersFromDB() {
        return AdminDAO.getAllManagers();
    }
    public List<User> getUserSalesAssistantFromDB() {
        return AdminDAO.getAllSalesAssistants();
    }
    
    public boolean deleteUser(String username, String userType) {
        boolean deleted = AdminDAO.deleteUser(username, userType);
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
