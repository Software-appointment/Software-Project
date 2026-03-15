package com.appointment.repository;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.TimeSlot;

import java.util.ArrayList;
import java.util.List;

public class RoomRepository {

    private List<MeetingRoom> rooms = new ArrayList<>();

    public void save(MeetingRoom room) {
        rooms.add(room);
    }

    public List<MeetingRoom> findAll() {
        return rooms;
    }

    public List<MeetingRoom> findAvailableRooms(String date, TimeSlot timeSlot) {
        List<MeetingRoom> available = new ArrayList<>();
        for (MeetingRoom room : rooms) {
            if (timeSlot.isAvailable()) {
                available.add(room);
            }
        }
        return available;
    }
}