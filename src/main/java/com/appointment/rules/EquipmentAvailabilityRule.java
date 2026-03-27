package com.appointment.rules;

import com.appointment.domain.Reservation;
import com.appointment.domain.Equipment;

import java.util.List;

public class EquipmentAvailabilityRule implements BookingRuleStrategy {

    /** List of equipment names requested by the user. */
    private List<String> requestedEquipment;

    /**
     * Constructs an EquipmentAvailabilityRule with the requested equipment list.
     *
     * @param requestedEquipment list of equipment names needed for the meeting
     */
    public EquipmentAvailabilityRule(List<String> requestedEquipment) {
        this.requestedEquipment = requestedEquipment;
    }

    /**
     * Checks whether all requested equipment is available in the reserved room.
     *
     * @param reservation the reservation to validate
     * @return true if all requested equipment is available, false otherwise
     */
    @Override
    public boolean isValid(Reservation reservation) {
        List<Equipment> roomEquipment = reservation.getRoom().getAvailableEquipment();

        for (String requested : requestedEquipment) {
            boolean found = false;
            for (Equipment equipment : roomEquipment) {
                if (equipment.getEquipmentName().equals(requested)
                        && equipment.isAvailable()) {
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