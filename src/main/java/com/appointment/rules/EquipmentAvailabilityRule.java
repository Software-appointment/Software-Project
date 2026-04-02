package com.appointment.rules;

import com.appointment.domain.Equipment;
import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;
import java.util.List;

/**
 * Booking rule that checks if requested equipment
 * is available in the selected meeting room.
 *
 * @author Student C
 * @version 1.0
 */
public class EquipmentAvailabilityRule implements BookingRuleStrategy {

    /**
     * Checks if all requested equipment is available in the room.
     *
     * @param reservation the reservation to validate
     * @return true if all equipment is available, false otherwise
     */
    @Override
    public boolean isValid(Reservation reservation) {
        MeetingRoom room = reservation.getRoom();
        List<Equipment> requestedEquipment = reservation.getEquipmentRequested();

        if (requestedEquipment == null || requestedEquipment.isEmpty()) {
            return true;
        }

        List<Equipment> availableEquipment = room.getAvailableEquipment();

        for (Equipment requested : requestedEquipment) {
            boolean found = false;
            for (Equipment available : availableEquipment) {
                if (available.getEquipmentName().equals(requested.getEquipmentName())
                        && available.isAvailable()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }
}
