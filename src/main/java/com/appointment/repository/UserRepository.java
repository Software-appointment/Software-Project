package com.appointment.repository;

import com.appointment.domain.Administrator;
import com.appointment.domain.User;
import java.util.LinkedList;
import java.util.List;

/**
 * Repository for managing users and administrators in memory.
 * Uses LinkedList as in-memory storage.
 *
 * @author Student A
 * @version 1.0
 */
public class UserRepository {

    /** In-memory list of all registered users. */
    private LinkedList<User> users;

    /** In-memory list of all administrators. */
    private LinkedList<Administrator> administrators;

    /**
     * Constructor initializes empty user and administrator lists.
     */
    public UserRepository() {
        this.users          = new LinkedList<>();
        this.administrators = new LinkedList<>();
    }

    /**
     * Adds a new user to the repository.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Returns all registered users.
     *
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Finds a user by their ID.
     *
     * @param userId the ID to search for
     * @return the user if found, null otherwise
     */
    public User findUserById(String userId) {
        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Adds a new administrator to the repository.
     *
     * @param admin the administrator to add
     */
    public void addAdministrator(Administrator admin) {
        administrators.add(admin);
    }

    /**
     * Finds an administrator by username.
     *
     * @param username the username to search for
     * @return the administrator if found, null otherwise
     */
    public Administrator findAdminByUsername(String username) {
        for (Administrator a : administrators) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Returns all administrators.
     *
     * @return list of all administrators
     */
    public List<Administrator> getAllAdministrators() {
        return administrators;
    }
}