package com.appointment.rules;

import com.appointment.domain.Reservation;

public class DurationRule implements BookingRuleStrategy {

    @Override
    public boolean isValid(Reservation reservation) {
        int duration = reservation.getDuration();
        return duration == 30 || duration == 60 || duration == 120;
    }
}