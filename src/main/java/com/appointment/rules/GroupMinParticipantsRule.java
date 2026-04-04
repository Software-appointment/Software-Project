package com.appointment.rules;

import com.appointment.domain.GroupReservation;
import com.appointment.domain.Reservation;

/**
 * Validates that a group reservation has at least 2 participants.
 *
 * @author Student B
 * @version 1.0
 */
public class GroupMinParticipantsRule implements BookingRuleStrategy {

    /**
     * Checks whether the group reservation has at least 2 participants.
     *
     * @param reservation the reservation to validate
     @return true if not a group or has at least 2 participants, false otherwise
     */
    @Override
    public boolean isValid(Reservation reservation) {
        if (reservation instanceof GroupReservation) {
            return reservation.getParticipantCount() >= 2;
        }
        return true;
    }
}