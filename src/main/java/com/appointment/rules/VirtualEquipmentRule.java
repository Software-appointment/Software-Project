package com.appointment.rules;

import com.appointment.domain.Equipment;
import com.appointment.domain.Reservation;
import com.appointment.domain.VirtualReservation;

/**
 * Validates that a virtual reservation has a Video Conference System in the room.
 *
 * @author Student B
 * @version 1.0
 */
public class VirtualEquipmentRule implements BookingRuleStrategy {

    /**
     * Checks whether the room has a Video Conference System for virtual reservations.
     *
     * @param reservation the reservation to validate
     * @return true if not virtual or room has Video Conference System, false otherwise
     */
    @Override
    public boolean isValid(Reservation reservation) {
        if (reservation instanceof VirtualReservation) {
            for (Equipment equipment : reservation.getRoom().getAvailableEquipment()) {
                if (equipment.getEquipmentName().equals("Video Conference System")
                        && equipment.isAvailable()) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
}