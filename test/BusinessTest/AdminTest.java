package BusinessTest;

import BusinessLayer.Admin;
import BusinessLayer.Role;
import BusinessLayer.User;
import DBLayer.AdminDAO;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class AdminTest {

    private Admin testAdmin;
    private MockAdminDAO mockAdminDAO;

    @Before
    public void setUp() {
        testAdmin = new Admin("TestAdmin", "TestPassword");
        mockAdminDAO = new MockAdminDAO();
        testAdmin.AdminDAO = mockAdminDAO;
    }

    @Test
    public void testAddUserManagerSuccess() throws SQLIntegrityConstraintViolationException {
        mockAdminDAO.setInsertManagerResult(true);
        String name = "ManagerUser";
        String username = "managerUsername";
        String password = "managerPassword";
        String userType = "Manager";

        assertTrue(testAdmin.addUser(name, username, password, userType));

        assertEquals(name, mockAdminDAO.getLastInsertedManager().getName());
        assertEquals(username, mockAdminDAO.getLastInsertedManager().getUsername());
        assertEquals(password, mockAdminDAO.getLastInsertedManager().getPassword());
    }
    

    @Test(expected = SQLIntegrityConstraintViolationException.class)
    public void testAddUserManagerFailure() throws SQLIntegrityConstraintViolationException {
        mockAdminDAO.setInsertManagerResult(false);
        testAdmin.addUser("ManagerUser2", "managerUsername2", "managerPassword", "Manager");
    }




    @Test
    public void testAddUserSalesAssistant() throws SQLIntegrityConstraintViolationException {
        mockAdminDAO.setInsertSalesAssistantResult(true);

        String name = "SalesUser";
        String username = "salesUsername";
        String password = "salesPassword";
        String userType = "Sales Assistant";

        assertTrue(testAdmin.addUser(name, username, password, userType));

        assertEquals(name, mockAdminDAO.getLastInsertedSalesAssistant().getName());
        assertEquals(username, mockAdminDAO.getLastInsertedSalesAssistant().getUsername());
        assertEquals(password, mockAdminDAO.getLastInsertedSalesAssistant().getPassword());
    }
    
    


    private static class MockAdminDAO extends AdminDAO {

        private boolean insertManagerResult;
        private boolean insertSalesAssistantResult;
        private User lastInsertedManager;
        private User lastInsertedSalesAssistant;

        public void setInsertManagerResult(boolean result) {
            
            this.insertManagerResult = result;
        }

        public void setInsertSalesAssistantResult(boolean result) {
            this.insertSalesAssistantResult = result;
        }

        @Override
        public void insertManager(User manager) throws SQLIntegrityConstraintViolationException {
           this.lastInsertedManager = manager;
            if (!insertManagerResult) {
                throw new SQLIntegrityConstraintViolationException("Mock insertManager failed");
            }
        }

        @Override
        public void insertSalesAssistant(User salesAssistant) throws SQLIntegrityConstraintViolationException {
            this.lastInsertedSalesAssistant = salesAssistant;
            if (!insertSalesAssistantResult) {
                throw new SQLIntegrityConstraintViolationException("Mock insertSalesAssistant failed");
            }
        }

        public User getLastInsertedManager() {
            return lastInsertedManager;
        }

        public User getLastInsertedSalesAssistant() {
            return lastInsertedSalesAssistant;
        }
    }
}



/*
public class AdminTest {

    private Admin testAdmin;
    private MockAdminDAO mockAdminDAO;

    @Before
    public void setUp() {
        testAdmin = new Admin("TestAdmin", "TestPassword");
        mockAdminDAO = new MockAdminDAO();
        testAdmin.AdminDAO = mockAdminDAO;
    }

    @Test
    public void testAddUserManagerSuccess() throws SQLIntegrityConstraintViolationException {
        mockAdminDAO.setInsertManagerResult(true);
        String name = "ManagerUser";
        String username = "managerUsername";
        String password = "managerPassword";
        String userType = "Manager";

        assertTrue(testAdmin.addUser(name, username, password, userType));

        assertEquals(name, mockAdminDAO.getLastInsertedManager().getName());
        assertEquals(username, mockAdminDAO.getLastInsertedManager().getUsername());
        assertEquals(password, mockAdminDAO.getLastInsertedManager().getPassword());
    }
    

    @Test(expected = SQLIntegrityConstraintViolationException.class)
    public void testAddUserManagerFailure() throws SQLIntegrityConstraintViolationException {
        mockAdminDAO.setInsertManagerResult(false);
        testAdmin.addUser("ManagerUser2", "managerUsername2", "managerPassword", "Manager");
    }




    @Test
    public void testAddUserSalesAssistant() throws SQLIntegrityConstraintViolationException {
        mockAdminDAO.setInsertSalesAssistantResult(true);

        String name = "SalesUser";
        String username = "salesUsername";
        String password = "salesPassword";
        String userType = "Sales Assistant";

        assertTrue(testAdmin.addUser(name, username, password, userType));

        assertEquals(name, mockAdminDAO.getLastInsertedSalesAssistant().getName());
        assertEquals(username, mockAdminDAO.getLastInsertedSalesAssistant().getUsername());
        assertEquals(password, mockAdminDAO.getLastInsertedSalesAssistant().getPassword());
    }
    
    


    private static class MockAdminDAO extends AdminDAO {

        private boolean insertManagerResult;
        private boolean insertSalesAssistantResult;
        private User lastInsertedManager;
        private User lastInsertedSalesAssistant;

        public void setInsertManagerResult(boolean result) {
            
            this.insertManagerResult = result;
        }

        public void setInsertSalesAssistantResult(boolean result) {
            this.insertSalesAssistantResult = result;
        }

        @Override
        public void insertManager(User manager) throws SQLIntegrityConstraintViolationException {
           this.lastInsertedManager = manager;
            if (!insertManagerResult) {
                throw new SQLIntegrityConstraintViolationException("Mock insertManager failed");
            }
        }

        @Override
        public void insertSalesAssistant(User salesAssistant) throws SQLIntegrityConstraintViolationException {
            this.lastInsertedSalesAssistant = salesAssistant;
            if (!insertSalesAssistantResult) {
                throw new SQLIntegrityConstraintViolationException("Mock insertSalesAssistant failed");
            }
        }

        public User getLastInsertedManager() {
            return lastInsertedManager;
        }

        public User getLastInsertedSalesAssistant() {
            return lastInsertedSalesAssistant;
        }
    }
}
*/