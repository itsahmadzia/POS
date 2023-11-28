/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package BusinessTest;

import BusinessLayer.Admin;
import BusinessLayer.Logout;
import BusinessLayer.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class LogoutTest {

    @Test
    public void testLogOutUser() {
        User user = new User("testUser", "testUser","password");
        user.setLoggedIn(true);

        assertTrue(user.isLoggedIn());
        Logout.logOut(user);
        assertFalse(user.isLoggedIn());
    }

    @Test
    public void testLogOutAdmin() {
        Admin admin = new Admin("testAdmin", "adminPassword");
        admin.setLoggedIn(true);

        assertTrue(admin.isLoggedIn());
        Logout.logOutAdmin(admin);
        assertFalse(admin.isLoggedIn());
    }
}
