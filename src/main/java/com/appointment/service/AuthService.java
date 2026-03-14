package com.appointment.service;

import com.appointment.domain.Administrator;
import com.appointment.repository.UserRepository;


public class AuthService {

    /** Repository used to look up administrator credentials. */
    private UserRepository userRepository;

   
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Attempts to log in an administrator using the given credentials.
     * If credentials are valid, sets the admin session as active.
     *
     
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
     * Clears the active session.
     *
    
     */
    public void logout(String username) {
        Administrator admin = userRepository.findAdminByUsername(username);

        if (admin != null) {
            admin.setLoggedIn(false);
        }
    }

    /**
     * Checks whether the administrator with the given username is currently logged in.
     *
    
     */
    public boolean isLoggedIn(String username) {
        Administrator admin = userRepository.findAdminByUsername(username);

        if (admin == null) {
            return false;
        }

        return admin.isLoggedIn();
    }
}