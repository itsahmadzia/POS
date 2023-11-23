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
            User managerUser = new User(name, username, password);
            Role managerRole = new Manager();
            managerUser.setRole(managerRole);
            AdminDAO.insertManager(managerUser);
            
        } else if ("Sales Assistant".equals(userType)) {
            User salesAssistantUser = new User(name, username, password);
            Role salesAssistantRole = new SalesAssistant();
            salesAssistantUser.setRole(salesAssistantRole);
            AdminDAO.insertSalesAssistant(salesAssistantUser);
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
    
    public boolean deleteUser(String username ,String roleType) {
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
