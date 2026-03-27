package com.appointment.domain;

/**
 * Represents a system administrator who manages rooms and reservations.
 *
 * @author Student A
 * @version 1.0
 */
public class Administrator {

    /** Unique identifier for the administrator. */
    private String adminId;

    /** Username used for login. */
    private String username;

    /** Password used for login. */
    private String password;

    /** Tracks whether the administrator is currently logged in. */
    private boolean loggedIn;

    /**
     * Constructor to create a new Administrator.
     * Session is inactive by default.
     *
     * @param adminId unique ID for the administrator
     * @param username the login username
     * @param password the login password
     */
    public Administrator(String adminId, String username, String password) {
        this.adminId  = adminId;
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    /**
     * @return the administrator ID
     */
    public String getAdminId() { return adminId; }

    /**
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * @return the password
     */
    public String getPassword() { return password; }

    /**
     * @return true if the administrator is logged in, false otherwise
     */
    public boolean isLoggedIn() { return loggedIn; }

    /**
     * @param loggedIn the login status to set
     */
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }

    /**
     * @return string representation of the administrator
     */
    @Override
    public String toString() {
        return "Administrator{adminId='" + adminId + "', username='" + username + "', loggedIn=" + loggedIn + "}";
    }
}