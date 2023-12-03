package test.DBTest;

import BusinessLayer.Admin;
import BusinessLayer.User;
import DBLayer.AdminDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.BusinessTest.DatabaseConnectionTest;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import static org.junit.Assert.*;

public class AdminDAOTest {

    private AdminDAO adminDAO;

    @Before
    public void setUp() {
        
         try {

            adminDAO = new AdminDAO(DatabaseConnectionTest.getConnection());
           
        clearTestData();
        insertInitialData();
        
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
            
            adminDAO.insertManager(new User("managerName", "managerUsername", "managerPassword"));
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
        User salesAssistant = new User("newSalesAssistantName", "newSalesAssistantUsername", "newSalesAssistantPassword");
        try {
            adminDAO.insertSalesAssistant(salesAssistant);
            List<User> salesAssistants = adminDAO.getAllSalesAssistants();
            assertTrue(salesAssistants.stream().anyMatch(u -> u.getUsername().equals("newSalesAssistantUsername")));
        } catch (SQLIntegrityConstraintViolationException e) {
            fail("Unexpected SQLIntegrityConstraintViolationException");
        }
    }

    @Test
    public void testGetAllManagers() {
        List<User> managers = adminDAO.getAllManagers();
        assertFalse(managers.isEmpty());
    }

    @Test
    public void testGetAllSalesAssistants() {
        List<User> salesAssistants = adminDAO.getAllSalesAssistants();
        assertFalse(salesAssistants.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserTableNameInvalidRole() {
        adminDAO.getUserTableName("InvalidRole");
    }

}
