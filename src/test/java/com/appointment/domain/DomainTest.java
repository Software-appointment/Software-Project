package com.appointment.domain;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for domain classes.
 *
 * @author Student B
 * @version 1.0
 */
public class DomainTest {

    @Test
    void testUser_gettersAndSetters() {
        User user = new User("U1", "Sadeel", "sadeel@test.com", "0599000000");
        assertEquals("U1", user.getUserId());
        assertEquals("Sadeel", user.getName());
        assertEquals("sadeel@test.com", user.getEmail());
        assertEquals("0599000000", user.getPhone());
    }

    @Test
    void testMeetingRoom_gettersAndSetters() {
        MeetingRoom room = new MeetingRoom("R1", "Small Room", 10, "Small", new ArrayList<>());
        assertEquals("R1", room.getRoomId());
        assertEquals("Small Room", room.getRoomName());
        assertEquals(10, room.getCapacity());
        assertEquals("Small", room.getSize());
    }

    @Test
    void testEquipment_gettersAndSetters() {
        Equipment equipment = new Equipment("E1", "Projector", true);
        assertEquals("E1", equipment.getEquipmentId());
        assertEquals("Projector", equipment.getEquipmentName());
        assertTrue(equipment.isAvailable());
    }

    @Test
    void testTimeSlot_gettersAndSetters() {
    	TimeSlot slot = new TimeSlot("T1", "09:00", "10:00");
        assertEquals("T1", slot.getSlotId());
        assertTrue(slot.isAvailable());
        slot.setAvailable(false);
        assertFalse(slot.isAvailable());
    }

    @Test
    void testReservation_settersAndGetters() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Small", new ArrayList<>());
        Reservation reservation = new Reservation("RES1", "2026-04-10", "10:00", 60, room) {
            @Override
            public String getType() { return "TEST"; }
        };
        assertEquals("RES1", reservation.getReservationId());
        assertEquals("Confirmed", reservation.getStatus());
        reservation.setStatus("Cancelled");
        assertEquals("Cancelled", reservation.getStatus());
    }
}