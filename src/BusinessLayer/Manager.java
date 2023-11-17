package BusinessLayer;
import DBLayer.ManagerDAO;

public class Manager extends Role {
    private boolean LoggedIn;
    private String name;
    private String password;
    
    public Manager(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public boolean isLoggedIn() {
        return LoggedIn;
    }
    
    public void setLoggedIn(boolean b) {
        LoggedIn = b;
    }
    @Override
    public void permissions() {
        System.out.println("Manager has access to manager-specific functionality");
    }
    
    public boolean authenticateFromDB(String username, String password){
        return ManagerDAO.authenticate(this, username, password);
    }
}
