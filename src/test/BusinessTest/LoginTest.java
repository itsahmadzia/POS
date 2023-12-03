/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template

package test.BusinessTest;


import BusinessLayer.Manager;
import BusinessLayer.SalesAssistant;
import BusinessLayer.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author nifrawahaj


public class LoginTest {

    private Login login;

    @Before
    public void setUp() {
        // Create some test users for the Login class
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("user1", "password1", new SalesAssistant()));
        users.add(new User("user2", "password2", new Manager()));
        users.add(new User("user3", "password3", new SalesAssistant()));

        login = new Login(users);
    }

    @Test
    public void testAuthenticateUserWithValidCredentials() {
        assertTrue(login.authenticateUser("user1", "password1"));
        assertTrue(login.authenticateUser("user2", "password2"));
        assertTrue(login.authenticateUser("user3", "password3"));
    }

    @Test
    public void testAuthenticateUserWithInvalidCredentials() {
        assertFalse(login.authenticateUser("user1", "wrongPassword"));
        assertFalse(login.authenticateUser("nonexistentUser", "password2"));
    }

    @Test
    public void testAuthenticateUserAfterSuccessfulLogin() {
        // Authenticate with valid credentials
        assertTrue(login.authenticateUser("user1", "password1"));

        // Try authenticating again with the same credentials
        assertFalse(login.authenticateUser("user1", "password1"));
    }
}
*/
