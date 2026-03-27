package com.appointment.domain;

/**
 * Abstract base class representing a reservation in the system.
 * All reservation types must extend this class.
 *
 * @author Student C
 * @version 1.0
 */
public abstract class Reservation {

    /** Unique identifier for the reservation */
    private String reservationId;

    /** Date of the reservation */
    private String date;

    /** Time of the reservation */
    private String time;

    /** Duration of the reservation in minutes */
    private int duration;

    /** The meeting room reserved */
    private MeetingRoom room;

    /** Status of the reservation: Confirmed, Cancelled, Completed */
    private String status;

    /** Type of the reservation */
    private String reservationType;

    /**
     * Constructor to create a new Reservation.
     *
     * @param reservationId unique ID for the reservation
     * @param date date of the reservation
     * @param time time of the reservation
     * @param duration duration in minutes
     * @param room the meeting room
     */
    public Reservation(String reservationId, String date, String time, int duration, MeetingRoom room) {
        this.reservationId = reservationId;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.room = room;
        this.status = "Confirmed";
    }

    /**
     * Returns the type of this reservation.
     *
     * @return reservation type as String
     */
    public abstract String getType();

    // Getters and Setters

    /**
     * @return the reservation ID
     */
    public String getReservationId() { return reservationId; }

    /**
     * @return the date
     */
    public String getDate() { return date; }

    /**
     * @param date the date to set
     */
    public void setDate(String date) { this.date = date; }

    /**
     * @return the time
     */
    public String getTime() { return time; }

    /**
     * @param time the time to set
     */
    public void setTime(String time) { this.time = time; }

    /**
     * @return the duration in minutes
     */
    public int getDuration() { return duration; }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) { this.duration = duration; }

    /**
     * @return the meeting room
     */
    public MeetingRoom getRoom() { return room; }

    /**
     * @param room the room to set
     */
    public void setRoom(MeetingRoom room) { this.room = room; }

    /**
     * @return the status
     */
    public String getStatus() { return status; }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) { this.status = status; }

    /**
     * @return the reservation type
     */
    public String getReservationType() { return reservationType; }

    /**
     * @param reservationType the type to set
     */
    public void setReservationType(String reservationType) { this.reservationType = reservationType; }
}