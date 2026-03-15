package com.appointment.domain;

import java.util.List;

public class MeetingRoom {

    private String roomId;
    private String roomName;
    private int capacity;
    private String size;
    private List<Equipment> availableEquipment;

    public MeetingRoom(String roomId, String roomName, int capacity,
                       String size, List<Equipment> availableEquipment) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.capacity = capacity;
        this.size = size;
        this.availableEquipment = availableEquipment;
    }

    public String getRoomId() { return roomId; }
    public String getRoomName() { return roomName; }
    public int getCapacity() { return capacity; }
    public String getSize() { return size; }
    public List<Equipment> getAvailableEquipment() { return availableEquipment; }
}