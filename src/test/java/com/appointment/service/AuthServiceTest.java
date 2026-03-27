package com.appointment.service;

import com.appointment.domain.Administrator;
import com.appointment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AuthServiceTest {

    private AuthService authService;
    private UserRepository userRepository;

    /**
     * Sets up a fresh repository and AuthService before each test.
     * Adds one test administrator with known credentials.
     */
    @BeforeEach
    public void setUp() {
        userRepository = new UserRepository();
        authService    = new AuthService(userRepository);

        // Add a test admin
        Administrator admin = new Administrator("A001", "admin", "1234");
        userRepository.addAdministrator(admin);
    }

    //  US1.1 Login Tests 

    @Test
    public void testLoginSuccess() {
        boolean result = authService.login("admin", "1234");
        assertTrue(result, "Login should succeed with correct credentials");
    }

    @Test
    public void testLoginWrongPassword() {
        boolean result = authService.login("admin", "wrongpass");
        assertFalse(result, "Login should fail with wrong password");
    }

    @Test
    public void testLoginWrongUsername() {
        boolean result = authService.login("unknown", "1234");
        assertFalse(result, "Login should fail with unknown username");
    }

    @Test
    public void testLoginSetsSessionActive() {
        authService.login("admin", "1234");
        assertTrue(authService.isLoggedIn("admin"), "Admin should be logged in after successful login");
    }

    @Test
    public void testNotLoggedInBeforeLogin() {
        assertFalse(authService.isLoggedIn("admin"), "Admin should not be logged in before login");
    }

    //  US1.2 Logout Tests 

    @Test
    public void testLogoutSuccess() {
        authService.login("admin", "1234");
        authService.logout("admin");
        assertFalse(authService.isLoggedIn("admin"), "Admin should not be logged in after logout");
    }

    @Test
    public void testLogoutRequiresRelogin() {
        authService.login("admin", "1234");
        authService.logout("admin");

        // After logout, must login again to be active
        assertFalse(authService.isLoggedIn("admin"), "After logout, session must be inactive");
        authService.login("admin", "1234");
        assertTrue(authService.isLoggedIn("admin"), "Admin should be active again after re-login");
    }
}