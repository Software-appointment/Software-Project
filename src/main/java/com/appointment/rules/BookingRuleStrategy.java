package com.appointment.rules;

import com.appointment.domain.Reservation;

/**
 * Strategy interface for booking validation rules.
 * Each rule must implement this interface.
 *
 * @author Student A
 * @version 1.0
 */
public interface BookingRuleStrategy {

    /**
     * Checks if the given reservation is valid according to this rule.
     *
     * @param reservation the reservation to validate
     * @return true if valid, false otherwise
     */
    boolean isValid(Reservation reservation);
}