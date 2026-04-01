package com.appointment.observer;

import com.appointment.domain.User;
import java.util.ArrayList;
import java.util.List;

public class SMSNotificationObserver implements Observer {

    private List<String> sentMessages = new ArrayList<>();

    @Override
    public void update(User user, String message) {
        String sms = "SMS to " + user.getPhone() + ": " + message;
        sentMessages.add(sms);
        System.out.println(sms);
    }

    public List<String> getSentMessages() {
        return sentMessages;
    }
}