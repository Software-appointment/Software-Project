package com.appointment.service;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.TimeSlot;
import com.appointment.repository.RoomRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoomServiceTest {

    private RoomService roomService;
    private RoomRepository roomRepository;
    private TimeSlot availableSlot;
    private TimeSlot unavailableSlot;

    @BeforeEach
    void setUp() {
        roomRepository = new RoomRepository();
        roomService = new RoomService(roomRepository);

        availableSlot = new TimeSlot("S1", "10:00", "11:00");

        unavailableSlot = new TimeSlot("S2", "12:00", "13:00");
        unavailableSlot.setAvailable(false);

        MeetingRoom room1 = new MeetingRoom("R1", "Small Room", 5,
                "Small", Collections.emptyList());
        MeetingRoom room2 = new MeetingRoom("R2", "Large Room", 20,
                "Large", Collections.emptyList());

        roomRepository.save(room1);
        roomRepository.save(room2);
    }

    @Test
    void testGetAllRooms_returnsAllRooms() {
        List<MeetingRoom> rooms = roomService.getAllRooms();
        assertEquals(2, rooms.size());
    }

    @Test
    void testGetAvailableRooms_whenSlotAvailable_returnsRooms() {
        List<MeetingRoom> available = roomService.getAvailableRooms("2026-03-15", availableSlot);
        assertFalse(available.isEmpty());
    }

    @Test
    void testGetAvailableRooms_whenSlotUnavailable_returnsEmpty() {
        List<MeetingRoom> available = roomService.getAvailableRooms("2026-03-15", unavailableSlot);
        assertTrue(available.isEmpty());
    }
}