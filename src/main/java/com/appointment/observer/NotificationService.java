package com.appointment.observer;

import com.appointment.domain.User;
import java.util.ArrayList;
import java.util.List;

public class NotificationService {

    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(User user, String message) {
        for (Observer observer : observers) {
            observer.update(user, message);
        }
    }
}