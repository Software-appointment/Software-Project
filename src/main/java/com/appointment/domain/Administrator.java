package com.appointment.domain;


public class Administrator {

    private String adminId;
    
    private String username;

    private String password;

    /** Tracks whether the administrator is currently logged in. */
    private boolean loggedIn;

  
    public Administrator(String adminId, String username, String password) {
        this.adminId  = adminId;
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public String getAdminId() { return adminId; }

   
    public String getUsername() { return username; }

   
    public String getPassword() { return password; }

    
    public boolean isLoggedIn() { return loggedIn; }

   
    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }

    @Override
    public String toString() {
        return "Administrator{adminId='" + adminId + "', username='" + username + "', loggedIn=" + loggedIn + "}";
    }
}