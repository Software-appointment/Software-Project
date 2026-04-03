package com.appointment.domain;

/**
 * Represents a follow-up reservation in the system.
 *
 * @author Student C
 * @version 1.0
 */
public class FollowUpReservation extends Reservation {

    /**
     * Constructor to create a FollowUpReservation.
     *
     * @param reservationId unique ID for the reservation
     * @param date date of the reservation
     * @param time time of the reservation
     * @param duration duration in minutes
     * @param room the meeting room
     */
    public FollowUpReservation(String reservationId, String date,
                                String time, int duration, MeetingRoom room) {
        super(reservationId, date, time, duration, room);
    }

    /**
     * @return the type of this reservation
     */
    @Override
    public String getType() {
        return "FOLLOW_UP";
    }
}