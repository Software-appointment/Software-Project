package com.appointment.rules;

<<<<<<< HEAD
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
=======
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
>>>>>>> 5562cb610a3a78a49ede58e53d1f0a55a0764a6b
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
<<<<<<< HEAD
}
=======
}
>>>>>>> 5562cb610a3a78a49ede58e53d1f0a55a0764a6b
