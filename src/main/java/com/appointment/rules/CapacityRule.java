package com.appointment.rules;

import com.appointment.domain.Reservation;


public class CapacityRule implements BookingRuleStrategy {

    /** The number of participants requesting the reservation. */
    private int participantCount;

    /**
     * Constructs a CapacityRule with the given number of participants.
     *
     * @param participantCount number of participants in the meeting
     */
    public CapacityRule(int participantCount) {
        this.participantCount = participantCount;
    }

    /**
     * Checks whether the room capacity is sufficient for the number of participants.
     *
     * @param reservation the reservation to validate
     * @return true if room capacity >= participantCount, false otherwise
     */
    @Override
    public boolean isValid(Reservation reservation) {
        int roomCapacity = reservation.getRoom().getCapacity();
        return roomCapacity >= participantCount;
    }
}