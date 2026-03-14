package com.appointment.repository;

import com.appointment.domain.Administrator;
import com.appointment.domain.User;

import java.util.LinkedList;
import java.util.List;


public class UserRepository {

    /** In-memory list of all registered users. */
    private LinkedList<User> users;

    /** In-memory list of all administrators. */
    private LinkedList<Administrator> administrators;

    
    public UserRepository() {
        this.users          = new LinkedList<>();
        this.administrators = new LinkedList<>();
    }

   
    public void addUser(User user) {
        users.add(user);
    }

    
    public List<User> getAllUsers() {
        return users;
    }

   
    public User findUserById(String userId) {
        for (User u : users) {
            if (u.getUserId().equals(userId)) {
                return u;
            }
        }
        return null;
    }

   
    public void addAdministrator(Administrator admin) {
        administrators.add(admin);
    }

    public Administrator findAdminByUsername(String username) {
        for (Administrator a : administrators) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

   
    public List<Administrator> getAllAdministrators() {
        return administrators;
    }
}