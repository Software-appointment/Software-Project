package com.appointment.service;

import com.appointment.domain.Administrator;
import com.appointment.repository.UserRepository;

/**
 * Service responsible for administrator authentication.
 * Handles login, logout, and session checking.
 *
 * @author Student A
 * @version 1.0
 */
public class AuthService {

    /** Repository used to look up administrator credentials. */
    private UserRepository userRepository;

    /**
     * Constructor to create AuthService with a user repository.
     *
     * @param userRepository the repository to look up admins
     */
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Attempts to log in an administrator using the given credentials.
     *
     * @param username the administrator username
     * @param password the administrator password
     * @return true if login successful, false otherwise
     */
    public boolean login(String username, String password) {
        Administrator admin = userRepository.findAdminByUsername(username);
        if (admin == null) {
            return false;
        }
        if (!admin.getPassword().equals(password)) {
            return false;
        }
        admin.setLoggedIn(true);
        return true;
    }

    /**
     * Logs out the administrator with the given username.
     *
     * @param username the administrator username
     */
    public void logout(String username) {
        Administrator admin = userRepository.findAdminByUsername(username);
        if (admin != null) {
            admin.setLoggedIn(false);
        }
    }

    /**
     * Checks whether the administrator is currently logged in.
     *
     * @param username the administrator username
     * @return true if logged in, false otherwise
     */
    public boolean isLoggedIn(String username) {
        Administrator admin = userRepository.findAdminByUsername(username);
        if (admin == null) {
            return false;
        }
        return admin.isLoggedIn();
    }
}