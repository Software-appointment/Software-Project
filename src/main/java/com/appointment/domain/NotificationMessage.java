package com.appointment.domain;

/**
 * Represents a notification message sent to users as a reminder.
 *
 * @author Student A
 * @version 1.0
 */
public class NotificationMessage {

    /** Unique identifier for the notification */
    private String notificationId;

    /** The message content */
    private String message;

    /** The date the message was sent */
    private String sendDate;

    /**
     * Constructor to create a new NotificationMessage.
     *
     * @param notificationId unique ID for the notification
     * @param message the message content
     * @param sendDate the date the message is sent
     */
    public NotificationMessage(String notificationId, String message, String sendDate) {
        this.notificationId = notificationId;
        this.message        = message;
        this.sendDate       = sendDate;
    }

    /**
     * @return the notification ID
     */
    public String getNotificationId() { return notificationId; }

    /**
     * @return the message content
     */
    public String getMessage() { return message; }

    /**
     * @return the send date
     */
    public String getSendDate() { return sendDate; }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) { this.message = message; }

    /**
     * @return string representation of the notification
     */
    @Override
    public String toString() {
        return "NotificationMessage{id='" + notificationId + "', date='" + sendDate + "', message='" + message + "'}";
    }
}