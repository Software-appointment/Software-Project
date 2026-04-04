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

    public SMSNotificationObserver() {
        this.sentMessages = new ArrayList<>();
    }

   
    @Override
    public void notify(User user, String message) {
        String fullMessage = "SMS to " + user.getPhone() + ": " + message;
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