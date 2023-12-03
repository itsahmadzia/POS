package BusinessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * The employeeDAO class provides data access methods for Employee objects in the database.
 */
public class employeeDAO {

    Connection connection ;
    /**
     * Constructs an EmployeeDAO object and initializes the database connection.
     *
     * @throws SQLException If there is an error establishing the database connection.
     */
    employeeDAO() throws SQLException {
        this.connection=DatabaseConnectivity.getConnection();
    }

     /**
     * Inserts an Employee object into the database.
     *
     * @param emp The Employee object to be inserted.
     */
    public void insertEmployee(Employee emp) {
        try {
            String query = "INSERT INTO employee_management.Employee (id, name,salary,role ) " +
                    "VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, emp.getId());
                    preparedStatement.setString(2, emp.getName());
                    preparedStatement.setDouble(3, emp.getSalary());
                    preparedStatement.setString(4, emp.getPosition());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a list of all employees from the database.
     *
     * @return List of Employee objects representing all employees in the database.
     */
    public List<Employee> getAllEmployees() {
        List<Employee>  allemployees= new ArrayList<>();
        try {
            String query = "SELECT *from employee_management.Employee";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

              ResultSet rs= preparedStatement.executeQuery();
              while (rs.next()){
                  int id=rs.getInt("id");
                  double salary=rs.getDouble("salary");
                  String name = rs.getString("name");
                  String position = rs.getString("role");
                  Employee temp = new Employee();
                  temp.setId(id);
                  temp.setName(name);
                  temp.setPosition(position);
                  temp.setSalary(salary);
                  allemployees.add(temp);
              }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allemployees;
    }

    /**
     * Deletes an employee from the database based on the provided ID.
     *
     * @param employeeId The ID of the employee to be deleted.
     */
    public void deleteEmployee(int rowdata) {
        try {
            String query = "DELETE FROM employee_management.Employee WHERE ID =?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, rowdata);

                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

