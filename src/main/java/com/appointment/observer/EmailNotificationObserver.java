package com.appointment.observer;

import com.appointment.domain.User;
import com.appointment.service.EmailService;
import java.util.ArrayList;
import java.util.List;

/**
 * Observer implementation that sends email notifications to users.
 * In test mode, records all sent messages instead of sending real emails.
 *
 * @author Student A
 * @version 1.0
 */
public class EmailNotificationObserver implements Observer {

    /** List of sent messages recorded in test mode. */
    private List<String> sentMessages;

    /** Whether this observer is running in test mode. */
    private boolean testMode;

    /** Email service for sending real emails. */
    private EmailService emailService;

    /**
     * Constructor for real mode — sends actual emails.
     */
    public EmailNotificationObserver() {
        this.sentMessages = new ArrayList<>();
        this.testMode = false;
        this.emailService = new EmailService();
    }

    /**
     * Constructor for test mode — records messages without sending.
     *
     * @param testMode true to enable test mode
     */
    public EmailNotificationObserver(boolean testMode) {
        this.sentMessages = new ArrayList<>();
        this.testMode = testMode;
    }

    /**
     * Sends an email notification to the given user.
     * In test mode, records the message instead of sending.
     *
     * @param user the user to notify
     * @param message the notification message content
     */
    @Override
    public void notify(User user, String message) {
        String fullMessage = "EMAIL to " + user.getEmail() + ": " + message;
        sentMessages.add(fullMessage);

        if (testMode) {
            System.out.println("[TEST MODE] " + fullMessage);
        } else {
            String subject = "Meeting Room Reservation - Notification";
            String body = "Dear " + user.getName() + ",\n\n"
                        + message + "\n\n"
                        + "Best regards,\nMeeting Room System";
            emailService.sendEmail(user.getEmail(), subject, body);
        }
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