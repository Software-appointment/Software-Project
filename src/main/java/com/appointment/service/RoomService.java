package com.appointment.service;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.TimeSlot;
import com.appointment.repository.RoomRepository;

import java.util.List;

public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<MeetingRoom> getAvailableRooms(String date, TimeSlot timeSlot) {
        return roomRepository.findAvailableRooms(date, timeSlot);
    }

    public List<MeetingRoom> getAllRooms() {
        return roomRepository.findAll();
    }
}