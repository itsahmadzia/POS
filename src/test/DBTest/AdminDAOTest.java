package test.DBTest;

import BusinessLayer.Admin;
import BusinessLayer.User;
import DBLayer.AdminDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.BusinessTest.DatabaseConnectionTest;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static org.junit.Assert.*;


public class AdminDAOTest {

    private AdminDAO adminDAO;
    private void clearTestDataALL() {
        try {
            String deleteProductsQuery = "DELETE FROM Product";
            try (PreparedStatement deleteProductsStatement = DatabaseConnectionTest.getConnection().prepareStatement(deleteProductsQuery)) {
                deleteProductsStatement.executeUpdate();
            }

        } catch (SQLException e) {
            // e.printStackTrace();
            System.err.println("Error during clear:");
        }

        try {
            String deleteCategoryQuery = "DELETE FROM Category";
            try (PreparedStatement deleteCategoryStatement = DatabaseConnectionTest.getConnection().prepareStatement(deleteCategoryQuery)) {
                deleteCategoryStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String deleteorderItemQuery = "DELETE FROM order_t_Item";
            try (PreparedStatement deleteorderItemStatement = DatabaseConnectionTest.getConnection().prepareStatement(deleteorderItemQuery)) {
                deleteorderItemStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String deleteorderQuery = "DELETE FROM order_t";
            try (PreparedStatement deleteOrderStatement = DatabaseConnectionTest.getConnection().prepareStatement(deleteorderQuery)) {
                deleteOrderStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String deleteOperatorQuery = "DELETE FROM Operator";
            try (PreparedStatement deleteOpStatement = DatabaseConnectionTest.getConnection().prepareStatement(deleteOperatorQuery)) {
                deleteOpStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String deleteOperatorQuery = "DELETE FROM Manager";
            try (PreparedStatement deleteOpStatement = DatabaseConnectionTest.getConnection().prepareStatement(deleteOperatorQuery)) {
                deleteOpStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setUp() {
        clearTestDataALL();
         try {
            adminDAO = new AdminDAO(DatabaseConnectionTest.getConnection());

            clearTestData();
           // insertInitialData();

            } catch (SQLException e) {
                e.printStackTrace();  
            }
    }

    @After
    public void tearDown() {
        
        clearTestData();
    }

    private void clearTestData() {
        try {
            adminDAO.deleteUser("managerName", "Manager");
            adminDAO.deleteUser("newManagerName", "Manager");
            adminDAO.deleteUser("salesAssistantName", "Operator");
            adminDAO.deleteUser("newSalesAssistantName", "Operator");
        } catch (SQLIntegrityConstraintViolationException e) {   
           // e.printStackTrace(); 
            System.out.println("Failed to clear test data: " + e.getMessage());
        }
    }

    private void insertInitialData() {
        try {
            
           // adminDAO.insertManager(new User("managerName", "managerUsername", "managerPassword"));
            adminDAO.insertSalesAssistant(new User("salesAssistantName", "salesAssistantUsername", "salesAssistantPassword"));
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Failed to insert initial data: " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    @Test
    public void testAuthenticateInvalidCredentials() {
        String username = "invalidUsername";
        String password = "invalidPassword";
        Admin admin = new Admin(username,password);

        assertFalse(adminDAO.authenticate(admin, username, password));
        assertFalse(admin.isLoggedIn());
    }

    @Test
    public void testInsertManager() {
        User manager = new User("newManagerName", "newManagerUsername", "newManagerPassword");
        try {
            adminDAO.insertManager(manager);
            List<User> managers = adminDAO.getAllManagers();
            assertTrue(managers.stream().anyMatch(u -> u.getUsername().equals("newManagerUsername")));
        } catch (SQLIntegrityConstraintViolationException e) {
            fail("Unexpected SQLIntegrityConstraintViolationException");
        }
    }

    @Test
    public void testInsertSalesAssistant() {
        User salesAssistant = new User("newSalesAssistantName", "this will now work", "newSalesAssistantPassword");
        try {
            adminDAO.insertSalesAssistant(salesAssistant);
            List<User> salesAssistants = adminDAO.getAllSalesAssistants();
            assertTrue(salesAssistants.stream().anyMatch(u -> u.getUsername().equals("this will now work")));
        } catch (SQLIntegrityConstraintViolationException e) {
            fail("Unexpected SQLIntegrityConstraintViolationException. User already exists in the database.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Unexpected exception occurred during insertion.");
        }
    }


   @Test
    public void testGetAllManagers() {
       User manager = new User("newManagerName", "newManagerUsername", "newManagerPassword");

       try {
           adminDAO.insertManager(manager);
       } catch (SQLIntegrityConstraintViolationException e) {
           throw new RuntimeException(e);
       }
       List<User> managers = adminDAO.getAllManagers();
        System.out.println("Actual Managers: " + managers);
        assertFalse(managers.isEmpty());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testGetUserTableNameInvalidRole() {
        adminDAO.getUserTableName("InvalidRole");
    }

}
