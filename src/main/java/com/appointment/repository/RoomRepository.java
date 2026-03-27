package com.appointment.repository;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.TimeSlot;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing meeting rooms in memory.
 * Uses ArrayList as in-memory storage.
 *
 * @author Student C
 * @version 1.0
 */
public class RoomRepository {

    /** In-memory list of all meeting rooms. */
    private List<MeetingRoom> rooms = new ArrayList<>();

    /**
     * Saves a new meeting room to the repository.
     *
     * @param room the meeting room to save
     */
    public void save(MeetingRoom room) {
        rooms.add(room);
    }

    /**
     * Returns all meeting rooms in the repository.
     *
     * @return list of all meeting rooms
     */
    public List<MeetingRoom> findAll() {
        return rooms;
    }

    /**
     * Finds all available meeting rooms for a given date and time slot.
     *
     * @param date the date to check availability
     * @param timeSlot the time slot to check
     * @return list of available meeting rooms
     */
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