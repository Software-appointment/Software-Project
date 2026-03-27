package com.appointment.service;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.TimeSlot;
import com.appointment.repository.RoomRepository;
import java.util.List;

/**
 * Service responsible for managing meeting room availability.
 *
 * @author Student B
 * @version 1.0
 */
public class RoomService {

    /** Repository used to access meeting room data. */
    private RoomRepository roomRepository;

    /**
     * Constructor to create RoomService with a room repository.
     *
     * @param roomRepository the repository to access rooms
     */
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Returns all available meeting rooms for a given date and time slot.
     *
     * @param date the date to check availability
     * @param timeSlot the time slot to check
     * @return list of available meeting rooms
     */
    public List<MeetingRoom> getAvailableRooms(String date, TimeSlot timeSlot) {
        return roomRepository.findAvailableRooms(date, timeSlot);
    }

    /**
     * Returns all meeting rooms in the system.
     *
     * @return list of all meeting rooms
     */
    public List<MeetingRoom> getAllRooms() {
        return roomRepository.findAll();
    }
}