package BusinessTest;

import BusinessLayer.Employee;
import BusinessLayer.employeeDAO;
import java.sql.SQLException;
import static org.junit.Assert.*;
import org.junit.Test;

public class EmployeeTest {

    @Test
    public void testSetAndGetId() {
        Employee employee = new Employee();
        employee.setId(1);
        assertEquals(1, employee.getId());
    }

    @Test
    public void testSetAndGetName() {
        Employee employee = new Employee();
        employee.setName("Jack Miller");
        assertEquals("Jack Miller", employee.getName());
    }

    @Test
    public void testSetAndGetPosition() {
        Employee employee = new Employee();
        employee.setPosition("Manager");
        assertEquals("Manager", employee.getPosition());
    }

    @Test
    public void testSetAndGetSalary() {
        Employee employee = new Employee();
        employee.setSalary(50000.0);
        assertEquals(50000.0, employee.getSalary(), 0.001); //0.001 delta for doublecomparison
    }

}
