package com.appointment.observer;

import com.appointment.domain.User;

public interface Observer {
    void update(User user, String message);
}