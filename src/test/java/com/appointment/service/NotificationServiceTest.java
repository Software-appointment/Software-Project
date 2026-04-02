package com.appointment.service;

import com.appointment.domain.User;
import com.appointment.observer.Observer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for NotificationService (US3.1).
 * Uses Mockito to mock observers and verify notifications.
 *
 * @author Student A
 * @version 1.0
 */
public class NotificationServiceTest {

    private NotificationService notificationService;
    private Observer mockObserver1;
    private Observer mockObserver2;
    private User testUser;

    @BeforeEach
    public void setUp() {
        notificationService = new NotificationService();
        mockObserver1 = Mockito.mock(Observer.class);
        mockObserver2 = Mockito.mock(Observer.class);
        testUser = new User("U001", "Jana", "jana@email.com", "0599000000");
    }

    @Test
    public void testAddObserver_increasesCount() {
        notificationService.addObserver(mockObserver1);
        assertEquals(1, notificationService.getObserverCount());
    }

    @Test
    public void testRemoveObserver_decreasesCount() {
        notificationService.addObserver(mockObserver1);
        notificationService.removeObserver(mockObserver1);
        assertEquals(0, notificationService.getObserverCount());
    }

    @Test
    public void testNotifyObservers_callsAllObservers() {
        notificationService.addObserver(mockObserver1);
        notificationService.addObserver(mockObserver2);

        notificationService.notifyObservers(testUser, "Your meeting starts in 30 minutes");

        // Verify both observers were notified exactly once
        verify(mockObserver1, times(1)).notify(testUser, "Your meeting starts in 30 minutes");
        verify(mockObserver2, times(1)).notify(testUser, "Your meeting starts in 30 minutes");
    }

    @Test
    public void testNotifyObservers_whenNoObservers_doesNotFail() {
        // Should not throw any exception
        assertDoesNotThrow(() ->
            notificationService.notifyObservers(testUser, "Test message")
        );
    }

    @Test
    public void testNotifyObservers_calledOnce_perObserver() {
        notificationService.addObserver(mockObserver1);
        notificationService.notifyObservers(testUser, "Reminder");

        verify(mockObserver1, times(1)).notify(any(User.class), anyString());
    }
}