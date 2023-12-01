package BusinessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class employeeDAO {

    Connection connection ;
    employeeDAO() throws SQLException {
        this.connection=DatabaseConnectivity.getConnection();
    }
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

