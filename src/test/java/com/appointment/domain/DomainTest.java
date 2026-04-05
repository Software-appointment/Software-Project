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

    @Test
    void testIndividualReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Small", new ArrayList<>());
        IndividualReservation r = new IndividualReservation("I1", "2026-04-10", "10:00", 60, room);
        assertEquals("INDIVIDUAL", r.getType());
    }

    @Test
    void testGroupReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Small", new ArrayList<>());
        GroupReservation r = new GroupReservation("G1", "2026-04-10", "10:00", 60, room, 3);
        assertEquals("GROUP", r.getType());
    }

    @Test
    void testVirtualReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Small", new ArrayList<>());
        VirtualReservation r = new VirtualReservation("V1", "2026-04-10", "10:00", 60, room);
        assertEquals("VIRTUAL", r.getType());
    }

    @Test
    void testUrgentReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Small", new ArrayList<>());
        UrgentReservation r = new UrgentReservation("U1", "2026-04-10", "10:00", 60, room);
        assertEquals("URGENT", r.getType());
    }

    @Test
    void testInPersonReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Small", new ArrayList<>());
        InPersonReservation r = new InPersonReservation("P1", "2026-04-10", "10:00", 60, room);
        assertEquals("IN_PERSON", r.getType());
    }
    
    @Test
    void testFollowUpReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Small", new ArrayList<>());
        FollowUpReservation r = new FollowUpReservation("F1", "2026-04-10", "10:00", 60, room);
        assertEquals("FOLLOW_UP", r.getType());
    }

    @Test
    void testAssessmentReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Small", new ArrayList<>());
        AssessmentReservation r = new AssessmentReservation("A1", "2026-04-10", "10:00", 60, room);
        assertEquals("ASSESSMENT", r.getType());
    }
    
    @Test
    void testAdministrator_gettersAndSetters() {
        Administrator admin = new Administrator("A1", "admin", "pass123");
        assertEquals("A1", admin.getAdminId());
        assertEquals("admin", admin.getUsername());
        assertFalse(admin.isLoggedIn());
        admin.setLoggedIn(true);
        assertTrue(admin.isLoggedIn());
    }

    @Test
    void testCateringService_gettersAndSetters() {
        CateringService catering = new CateringService("C1", "Lunch", 50.0);
        assertEquals("C1", catering.getCateringId());
        assertEquals("Lunch", catering.getServiceType());
        assertEquals(50.0, catering.getPrice());
    }
}