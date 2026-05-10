package com.appointment.domain;

/**
 * Represents a group meeting room reservation.
 * Requires a minimum of 2 participants.
 *
 * @author Student B
 * @version 1.0
 */
public class GroupReservation extends Reservation {

    /**
     * Constructs a GroupReservation.
     *
     * @param reservationId unique ID for the reservation
     * @param date date of the reservation
     * @param time time of the reservation
     * @param duration duration in minutes
     * @param room the meeting room
     * @param participantCount number of participants
     */
    public GroupReservation(String reservationId, String date, String time,
                            int duration, MeetingRoom room, int participantCount) {
        super(reservationId, date, time, duration, room);
        setParticipantCount(participantCount);
    }

    /**
     * Returns the type of this reservation.
     *
     * @return "GROUP"
     */
    @Override
    public String getType() {
        return "GROUP";
    }
}
