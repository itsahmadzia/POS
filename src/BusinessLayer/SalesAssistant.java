package BusinessLayer;

import DBLayer.SalesAssistantDAO;

public class SalesAssistant extends Role {
    private boolean LoggedIn;
    private String name;
    private String password;
    
    public SalesAssistant(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public boolean isLoggedIn() {
        return LoggedIn;
    }
    
    public void setLoggedIn(boolean b) {
        LoggedIn = b;
    }
        
    public boolean authenticateFromDB(String username, String password){
        return SalesAssistantDAO.authenticate(this, username, password);
    }
   
    @Override
    public void permissions() {
        System.out.println("Sales Assistant granted access to sales assistant-specific functionality");
    }

    public void processOrder() {
       
    }
}
