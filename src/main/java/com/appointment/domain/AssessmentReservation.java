package com.appointment.domain;

/**
 * Represents an assessment reservation in the system.
 *
 * @author Student C
 * @version 1.0
 */
public class AssessmentReservation extends Reservation {

    /**
     * Constructor to create an AssessmentReservation.
     *
     * @param reservationId unique ID for the reservation
     * @param date date of the reservation
     * @param time time of the reservation
     * @param duration duration in minutes
     * @param room the meeting room
     */
    public AssessmentReservation(String reservationId, String date,
                                  String time, int duration, MeetingRoom room) {
        super(reservationId, date, time, duration, room);
    }

    /**
     * @return the type of this reservation
     */
    @Override
    public String getType() {
        return "ASSESSMENT";
    }
}