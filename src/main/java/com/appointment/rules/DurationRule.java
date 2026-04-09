package com.appointment.rules;

import com.appointment.domain.Reservation;

/**
 * Validates that the reservation duration is one of the allowed values:
 * 30, 60, or 120 minutes.
 *
 * @author Student B
 * @version 1.0
 */
public class DurationRule implements BookingRuleStrategy {

    /**
     * Checks whether the reservation duration is valid.
     *
     * @param reservation the reservation to validate
     * @return true if duration is 30, 60, or 120 minutes; false otherwise
     */
    @Override
    public boolean isValid(Reservation reservation) {
        int duration = reservation.getDuration();
        return duration == 30 || duration == 60 || duration == 120;
    }
}