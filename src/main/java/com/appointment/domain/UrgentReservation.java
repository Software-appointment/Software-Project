package com.appointment.domain;

/**
 * Represents an urgent meeting room reservation.
 *
 * @author Student B
 * @version 1.0
 */
public class UrgentReservation extends Reservation {

    /**
     * Constructs an UrgentReservation.
     *
     * @param reservationId unique ID for the reservation
     * @param date date of the reservation
     * @param time time of the reservation
     * @param duration duration in minutes
     * @param room the meeting room
     */
    public UrgentReservation(String reservationId, String date, String time, int duration, MeetingRoom room) {
        super(reservationId, date, time, duration, room);
    }

    /**
     * Returns the type of this reservation.
     *
     * @return "URGENT"
     */
    @Override
    public String getType() {
        return "URGENT";
    }
}