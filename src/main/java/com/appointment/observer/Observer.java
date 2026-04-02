package com.appointment.observer;

import com.appointment.domain.User;

/**
 * Observer interface for the notification system.
 * Any class that wants to receive notifications must implement this interface.
 */
public interface Observer {

    /**
     * Sends a notification message to the given user.
     *
     * @param user    the user to notify
     * @param message the notification message content
     */
    void notify(User user, String message);
}