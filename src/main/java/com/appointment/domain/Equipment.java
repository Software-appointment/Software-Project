package com.appointment.domain;

/**
 * Represents equipment available in a meeting room.
 *
 * @author Student B
 * @version 1.0
 */
public class Equipment {

    /** Unique identifier for the equipment. */
    private String equipmentId;

    /** Name of the equipment (e.g. Projector, Whiteboard). */
    private String equipmentName;

    /** Whether the equipment is currently available. */
    private boolean available;

    /**
     * Constructor to create a new Equipment.
     *
     * @param equipmentId unique ID for the equipment
     * @param equipmentName name of the equipment
     * @param available availability status of the equipment
     */
    public Equipment(String equipmentId, String equipmentName, boolean available) {
        this.equipmentId   = equipmentId;
        this.equipmentName = equipmentName;
        this.available     = available;
    }

    /**
     * @return the equipment ID
     */
    public String getEquipmentId() { return equipmentId; }

    /**
     * @return the equipment name
     */
    public String getEquipmentName() { return equipmentName; }

    /**
     * @return true if the equipment is available, false otherwise
     */
    public boolean isAvailable() { return available; }

    /**
     * @param available the availability to set
     */
    public void setAvailable(boolean available) { this.available = available; }
}