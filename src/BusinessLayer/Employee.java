package BusinessLayer;

import java.sql.SQLException;

/**
 * The Employee class represents an employee in the system.
 * It contains information such as employee ID, name, position, and salary.
 */
public class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;

    /**
     * Sets the employee ID.
     *
     * @param id The ID to be set for the employee.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets the name of the employee.
     *
     * @param name The name to be set for the employee.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the position of the employee.
     *
     * @param position The position to be set for the employee.
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Sets the salary of the employee.
     *
     * @param salary The salary to be set for the employee.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Gets the name of the employee.
     *
     * @return The name of the employee.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the salary of the employee.
     *
     * @return The salary of the employee.
     */
    public double getSalary() {
        return salary;
    }

    /**
     * Gets the ID of the employee.
     *
     * @return The ID of the employee.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the position of the employee.
     *
     * @return The position of the employee.
     */
    public String getPosition() {
        return position;
    }

    /**
     * Example usage of the Employee class.
     *
     * @param args Command line arguments (not used in this example).
     * @throws SQLException If there is an SQL exception during database operations.
     */
    public static void main(String[] args) throws SQLException {
        employeeDAO employeeDAO = new employeeDAO();

        Employee employee = new Employee();
        employee.setSalary(12);
        employee.setPosition("Director");
        employee.setName("AHMAD");
        employee.setId(1);

        employeeDAO.insertEmployee(employee);
    }
}
