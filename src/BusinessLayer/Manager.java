public class Manager extends Role {
   
    public Manager(){
        log = "Manager";
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
}