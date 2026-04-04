package com.appointment.service;

import com.appointment.domain.User;
import com.appointment.observer.Observer;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for managing and triggering notifications.
 * Uses the Observer Pattern to notify all registered observers.
 *
 * @author Student A
 * @version 1.0
 */
public class NotificationService {

    /** List of registered observers. */
    private List<Observer> observers;

    /**
     * Constructor initializes empty observers list.
     */
    public NotificationService() {
        this.observers = new ArrayList<>();
    }

    /**
     * Adds an observer to the notification list.
     *
     * @param observer the observer to add
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Removes an observer from the notification list.
     *
     * @param observer the observer to remove
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers with the given message.
     *
     * @param user the user to notify
     * @param message the notification message
     */
    public void notifyObservers(User user, String message) {
        for (Observer observer : observers) {
            observer.notify(user, message);
        }
    }

    /**
     * Returns the number of registered observers.
     *
     * @return observer count
     */
    public int getObserverCount() {
        return observers.size();
    }
}