package com.appointment.domain;

/**
 * Represents a virtual (online) meeting room reservation.
 * Requires a Video Conference System in the room.
 *
 * @author Student B
 * @version 1.0
 */
public class VirtualReservation extends Reservation {

    /**
     * Constructs a VirtualReservation.
     *
     * @param reservationId unique ID for the reservation
     * @param date date of the reservation
     * @param time time of the reservation
     * @param duration duration in minutes
     * @param room the meeting room
     */
    public VirtualReservation(String reservationId, String date, String time, int duration, MeetingRoom room) {
        super(reservationId, date, time, duration, room);
    }

    /**
     * Returns the type of this reservation.
     *
     * @return "VIRTUAL"
     */
    @Override
    public String getType() {
        return "VIRTUAL";
    }
}