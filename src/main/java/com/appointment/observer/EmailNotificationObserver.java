package com.appointment.observer;

import com.appointment.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer implementation that sends email notifications to users.
 * In test mode, records all sent messages instead of sending real emails.
 */
public class EmailNotificationObserver implements Observer {

    /** List of sent messages recorded in test mode. */
    private List<String> sentMessages;

    public EmailNotificationObserver() {
        this.sentMessages = new ArrayList<>();
    }

    /**
     * Sends an email notification to the given user.
     * In test mode, records the message in sentMessages list.
     *
     * @param user    the user to notify
     * @param message the notification message content
     */
    @Override
    public void notify(User user, String message) {
        String fullMessage = "EMAIL to " + user.getEmail() + ": " + message;
        sentMessages.add(fullMessage);
        System.out.println(fullMessage);
    }

    public List<String> getSentMessages() {
        return sentMessages;
    }

    public void clearMessages() {
        sentMessages.clear();
    }
}