package com.appointment.rules;

import com.appointment.domain.Reservation;

/**
 * Strategy interface for booking validation rules.
 * Each rule implementation checks whether a reservation is valid.
 *
 * @author Student A
 * @version 1.0
 */
public interface BookingRuleStrategy {

    /**
     * Checks whether the given reservation is valid according to this rule.
     *
     * @param reservation the reservation to validate
     * @return true if the reservation passes this rule, false otherwise
     */
    boolean isValid(Reservation reservation);
}