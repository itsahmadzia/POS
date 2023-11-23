package BusinessLayer;

public class Manager extends Role {
    @Override
    public void permissions() {
        System.out.println("Manager has access to manager-specific functionality");
    }
    
    public String getRoleType() {
        return "Manager";
    }
}
