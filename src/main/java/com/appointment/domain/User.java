package com.appointment.domain;

/**
 * Represents a user who can reserve meeting rooms in the system.
 *
 * @author Student C
 * @version 1.0
 */
public class User {

    /** Unique identifier for the user. */
    private String userId;

    /** Full name of the user. */
    private String name;

    /** Email address of the user. */
    private String email;

    /** Phone number of the user. */
    private String phone;

    /**
     * Constructor to create a new User.
     *
     * @param userId unique ID for the user
     * @param name full name of the user
     * @param email email address of the user
     * @param phone phone number of the user
     */
    public User(String userId, String name, String email, String phone) {
        this.userId = userId;
        this.name   = name;
        this.email  = email;
        this.phone  = phone;
    }

    /**
     * @return the user ID
     */
    public String getUserId() { return userId; }

    /**
     * @return the user's full name
     */
    public String getName() { return name; }

    /**
     * @return the user's email
     */
    public String getEmail() { return email; }

    /**
     * @return the user's phone number
     */
    public String getPhone() { return phone; }

    /**
     * @param name the name to set
     */
    public void setName(String name) { this.name = name; }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * @return string representation of the user
     */
    @Override
    public String toString() {
        return "User{userId='" + userId + "', name='" + name + "', email='" + email + "'}";
    }
}