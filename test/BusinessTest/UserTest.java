
package BusinessTest;

import BusinessLayer.Manager;
import BusinessLayer.Role;
import BusinessLayer.SalesAssistant;
import BusinessLayer.User;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * @author nifrawahaj
 */

public class UserTest {
    private User user;
    @Before
    public void setUp() {
        Role roleInstance = new Manager();
        user = new User("manager1", "manager1password", roleInstance);
    }
    
    @Test
    public void testRoleType() {
        assertEquals("Manager", user.getRole().getRoleType());
    }
    
    @Test
    public void testLogin() {
        assertTrue(user.login("manager1", "manager1password"));
        assertFalse(user.login("manager1", "wrongPassword"));
    }

    @Test
    public void testSave() {
        ArrayList<User> userList = new ArrayList<>();
        user.save(userList);
        assertTrue(userList.contains(user));
    }

    @Test
    public void testLoggedInStatus() {
        assertFalse(user.isLoggedIn());
        user.setLoggedIn(true);

        assertTrue(user.isLoggedIn());
        user.setLoggedIn(false);

        assertFalse(user.isLoggedIn());
    }

    @Test
    public void testRole() {
        Role role = new SalesAssistant();
        user.setRole(role);
        assertEquals(role, user.getRole());
    }

    @After
    public void tearDown() {
    }
}
