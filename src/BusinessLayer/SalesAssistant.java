package BusinessLayer;

public class SalesAssistant extends Role {
     
    @Override
    public void permissions() {
        System.out.println("Sales Assistant granted access to sales assistant-specific functionality");
    }

    public void processOrder() {
       
    }
    @Override
    public String getRoleType() {
        return "Operator"; //since table in db is operator
    }
}
