package com.appointment.domain;
import com.appointment.domain.Equipment;
import java.util.List;
/**
 * Abstract base class representing a reservation in the system.
 * All reservation types must extend this class.
 *
 * @author Student C
 * @version 1.0
 */
public abstract class Reservation {
	
    /** List of equipment requested for this reservation. */
    private List<Equipment> equipmentRequested;
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
    /** Number of participants in the reservation */
    private int participantCount;

    public Reservation(String reservationId, String date, String time, int duration, MeetingRoom room) {
        this.reservationId = reservationId;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.room = room;
        this.status = "Confirmed";
    }

    public abstract String getType();

    public String getReservationId() { return reservationId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    public MeetingRoom getRoom() { return room; }
    public void setRoom(MeetingRoom room) { this.room = room; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getReservationType() { return reservationType; }
    public void setReservationType(String reservationType) { this.reservationType = reservationType; }
    public List<Equipment> getEquipmentRequested() { return equipmentRequested; }
    public void setEquipmentRequested(List<Equipment> equipmentRequested) { 
        this.equipmentRequested = equipmentRequested; 
    }
    public int getParticipantCount() { return participantCount; }
    public void setParticipantCount(int participantCount) { 
        this.participantCount = participantCount; 
    }
}