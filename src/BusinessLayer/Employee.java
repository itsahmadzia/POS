package BusinessLayer;

import java.sql.SQLException;

public class Employee {
    private int  id;
    private String name ;
    private String position ;
    private double salary ;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }



    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public static void main(String[] args) throws SQLException {
        employeeDAO dd= new employeeDAO();
        Employee e=new Employee();
        e.setSalary(12);
        e.setPosition("Director");
        e.setName("AHMAD");
        e.setId(1);
        dd.insertEmployee(e);

    }
}
