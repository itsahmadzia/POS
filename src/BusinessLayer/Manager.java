package BusinessLayer;

import DBLayer.ManagerDAO;

public class Manager extends Role {
   
    public Manager(){
        log = "Manager";
    }

    public Manager(String username, String password) {
        super();
    }

    @Override
    public void permissions() {
        System.out.println("Manager has access to manager specific functionality");
    }
    public String getLog() {
        return log;
    }
    public void setLog(String log) {
        this.log = log;
    }
    public boolean authenticateFromDB(String username, String password){
        return ManagerDAO.authenticate(this, username, password);
    }
}
