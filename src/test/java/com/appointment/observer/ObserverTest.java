package com.appointment.observer;

import com.appointment.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EmailNotificationObserver and SMSNotificationObserver.
 *
 * @author Student A
 * @version 1.0
 */
public class ObserverTest {

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("U001", "Jana", "jana@email.com", "0599000000");
    }

    @Test
    public void testEmailObserver_sendsMessage() {
        EmailNotificationObserver emailObserver = new EmailNotificationObserver();
        emailObserver.notify(testUser, "Meeting reminder");
        assertEquals(1, emailObserver.getSentMessages().size());
    }

    @Test
    public void testEmailObserver_messageContainsEmail() {
        EmailNotificationObserver emailObserver = new EmailNotificationObserver();
        emailObserver.notify(testUser, "Meeting reminder");
        assertTrue(emailObserver.getSentMessages().get(0).contains("jana@email.com"));
    }

    @Test
    public void testEmailObserver_clearMessages() {
        EmailNotificationObserver emailObserver = new EmailNotificationObserver();
        emailObserver.notify(testUser, "Meeting reminder");
        emailObserver.clearMessages();
        assertEquals(0, emailObserver.getSentMessages().size());
    }

    @Test
    public void testSMSObserver_sendsMessage() {
        SMSNotificationObserver smsObserver = new SMSNotificationObserver();
        smsObserver.notify(testUser, "Meeting reminder");
        assertEquals(1, smsObserver.getSentMessages().size());
    }

    @Test
    public void testSMSObserver_messageContainsPhone() {
        SMSNotificationObserver smsObserver = new SMSNotificationObserver();
        smsObserver.notify(testUser, "Meeting reminder");
        assertTrue(smsObserver.getSentMessages().get(0).contains("0599000000"));
    }

    @Test
    public void testSMSObserver_clearMessages() {
        SMSNotificationObserver smsObserver = new SMSNotificationObserver();
        smsObserver.notify(testUser, "Meeting reminder");
        smsObserver.clearMessages();
        assertEquals(0, smsObserver.getSentMessages().size());
    }
}