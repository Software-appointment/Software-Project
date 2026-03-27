package com.appointment.domain;

import java.util.List;

/**
 * Represents a meeting room available for reservation.
 *
 * @author Student B
 * @version 1.0
 */
public class MeetingRoom {

    /** Unique identifier for the meeting room. */
    private String roomId;

    /** Name of the meeting room. */
    private String roomName;

    /** Maximum number of participants the room can hold. */
    private int capacity;

    /** Size of the room (e.g. Small, Medium, Large). */
    private String size;

    /** List of equipment available in the room. */
    private List<Equipment> availableEquipment;

    /**
     * Constructor to create a new MeetingRoom.
     *
     * @param roomId unique ID for the room
     * @param roomName name of the room
     * @param capacity maximum number of participants
     * @param size size of the room
     * @param availableEquipment list of available equipment
     */
    public MeetingRoom(String roomId, String roomName, int capacity,
                       String size, List<Equipment> availableEquipment) {
        this.roomId             = roomId;
        this.roomName           = roomName;
        this.capacity           = capacity;
        this.size               = size;
        this.availableEquipment = availableEquipment;
    }

    /**
     * @return the room ID
     */
    public String getRoomId() { return roomId; }

    /**
     * @return the room name
     */
    public String getRoomName() { return roomName; }

    /**
     * @return the room capacity
     */
    public int getCapacity() { return capacity; }

    /**
     * @return the room size
     */
    public String getSize() { return size; }

    /**
     * @return the list of available equipment
     */
    public List<Equipment> getAvailableEquipment() { return availableEquipment; }
}