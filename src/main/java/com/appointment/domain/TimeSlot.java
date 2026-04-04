package com.appointment.domain;

/**
 * Represents a time slot available for meeting room reservations.
 *
 * @author Student A
 * @version 1.0
 */
public class TimeSlot {

    /** Unique identifier for the time slot. */
    private String slotId;

    /** Start time of the slot. */
    private String startTime;

    /** End time of the slot. */
    private String endTime;

    /** Whether this time slot is available for booking. */
    private boolean available;

    /**
     * Constructor to create a new TimeSlot.
     * Slot is available by default.
     *
     * @param slotId unique ID for the slot
     * @param startTime start time of the slot
     * @param endTime end time of the slot
     */
    public TimeSlot(String slotId, String startTime, String endTime) {
        this.slotId    = slotId;
        this.startTime = startTime;
        this.endTime   = endTime;
        this.available = true;
    }

    /**
     * @return the slot ID
     */
    public String getSlotId() { return slotId; }

    /**
     * @return the start time
     */
    public String getStartTime() { return startTime; }

    /**
     * @return the end time
     */
    public String getEndTime() { return endTime; }

    /**
     * @return true if the slot is available, false otherwise
     */
    public boolean isAvailable() { return available; }

    /**
     * @param available the availability to set
     */
    public void setAvailable(boolean available) { this.available = available; }

    /**
     * @return string representation of the time slot
     */
    @Override
    public String toString() {
        return "TimeSlot{" + startTime + " - " + endTime + ", available=" + available + "}";
    }
}