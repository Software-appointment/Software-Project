package com.appointment.domain;

public class NotificationMessage {

    
    private String notificationId;

    
    private String message;

    
    private String sendDate;

    public NotificationMessage(String notificationId, String message, String sendDate) {
        this.notificationId = notificationId;
        this.message        = message;
        this.sendDate       = sendDate;
    }

    
    public String getNotificationId() { return notificationId; }

    
    public String getMessage() { return message; }

       public String getSendDate() { return sendDate; }

    
    public void setMessage(String message) { this.message = message; }

    @Override
    public String toString() {
        return "NotificationMessage{id='" + notificationId + "', date='" + sendDate + "', message='" + message + "'}";
    }}
