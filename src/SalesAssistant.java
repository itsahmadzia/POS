public class SalesAssistant extends Role {
    public SalesAssistant(){
        log = "Sales Assistant";
    }
    @Override
    public void permissions() {
        System.out.println("Sales Assistant granted access to sales assistant-specific functionality");
    }
    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
    public void processOrder(){
    }
}