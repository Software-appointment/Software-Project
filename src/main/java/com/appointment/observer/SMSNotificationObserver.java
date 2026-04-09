package com.appointment.observer;

import com.appointment.domain.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Observer implementation that sends SMS notifications to users.
 * In test mode, records all sent messages instead of sending real SMS.
 *
 * @author Student A
 * @version 1.0
 */
public class SMSNotificationObserver implements Observer {

    /** List of sent messages recorded in test mode. */
    private List<String> sentMessages;

    /**
     * Constructor initializes empty sent messages list.
     */
    public SMSNotificationObserver() {
        this.sentMessages = new ArrayList<>();
    }

    /**
     * Sends an SMS notification to the given user.
     * In test mode, records the message in sentMessages list.
     *
     * @param user the user to notify
     * @param message the notification message content
     */
    @Override
    public void notify(User user, String message) {
        String fullMessage = "SMS to " + user.getPhone() + ": " + message;
        sentMessages.add(fullMessage);
        System.out.println(fullMessage);
    }

    /**
     * Returns all sent messages recorded in test mode.
     *
     * @return list of sent messages
     */
    public List<String> getSentMessages() {
        return sentMessages;
    }

    /**
     * Clears all recorded messages.
     */
    public void clearMessages() {
        sentMessages.clear();
    }
}