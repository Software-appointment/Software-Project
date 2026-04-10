package com.appointment.domain;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for all domain classes to improve code coverage.
 *
 * @author Student A
 * @version 1.0
 */
public class DomainTest {

    // ─── User ────────────────────────────────────────────────────

    @Test
    public void testUser_gettersAndSetters() {
        User user = new User("U001", "Jana", "jana@email.com", "0599000000");
        assertEquals("U001", user.getUserId());
        assertEquals("Jana", user.getName());
        assertEquals("jana@email.com", user.getEmail());
        assertEquals("0599000000", user.getPhone());

        user.setName("Sara");
        user.setEmail("sara@email.com");
        user.setPhone("0598000000");

        assertEquals("Sara", user.getName());
        assertEquals("sara@email.com", user.getEmail());
        assertEquals("0598000000", user.getPhone());
    }

    @Test
    public void testUser_toString() {
        User user = new User("U001", "Jana", "jana@email.com", "0599000000");
        assertTrue(user.toString().contains("Jana"));
    }

    // ─── Administrator ───────────────────────────────────────────

    @Test
    public void testAdministrator_gettersAndSetters() {
        Administrator admin = new Administrator("A001", "admin", "1234");
        assertEquals("A001", admin.getAdminId());
        assertEquals("admin", admin.getUsername());
        assertEquals("1234", admin.getPassword());
        assertFalse(admin.isLoggedIn());

        admin.setLoggedIn(true);
        assertTrue(admin.isLoggedIn());

        admin.setLoggedIn(false);
        assertFalse(admin.isLoggedIn());
    }

    @Test
    public void testAdministrator_toString() {
        Administrator admin = new Administrator("A001", "admin", "1234");
        assertTrue(admin.toString().contains("admin"));
    }

    // ─── TimeSlot ────────────────────────────────────────────────

    @Test
    public void testTimeSlot_gettersAndSetters() {
        TimeSlot slot = new TimeSlot("S001", "09:00", "10:00");
        assertEquals("S001", slot.getSlotId());
        assertEquals("09:00", slot.getStartTime());
        assertEquals("10:00", slot.getEndTime());
        assertTrue(slot.isAvailable());

        slot.setAvailable(false);
        assertFalse(slot.isAvailable());
    }

    @Test
    public void testTimeSlot_toString() {
        TimeSlot slot = new TimeSlot("S001", "09:00", "10:00");
        assertTrue(slot.toString().contains("09:00"));
    }

    // ─── NotificationMessage ─────────────────────────────────────

    @Test
    public void testNotificationMessage_gettersAndSetters() {
        NotificationMessage msg = new NotificationMessage("N001", "Reminder", "2026-04-09");
        assertEquals("N001", msg.getNotificationId());
        assertEquals("Reminder", msg.getMessage());
        assertEquals("2026-04-09", msg.getSendDate());

        msg.setMessage("Updated Reminder");
        assertEquals("Updated Reminder", msg.getMessage());
    }

    @Test
    public void testNotificationMessage_toString() {
        NotificationMessage msg = new NotificationMessage("N001", "Reminder", "2026-04-09");
        assertTrue(msg.toString().contains("Reminder"));
    }

    // ─── MeetingRoom ─────────────────────────────────────────────

    @Test
    public void testMeetingRoom_getters() {
        Equipment projector = new Equipment("E001", "Projector", true);
        MeetingRoom room = new MeetingRoom("R001", "Small Room", 10,
                "Small", Arrays.asList(projector));
        assertEquals("R001", room.getRoomId());
        assertEquals("Small Room", room.getRoomName());
        assertEquals(10, room.getCapacity());
        assertEquals("Small", room.getSize());
        assertEquals(1, room.getAvailableEquipment().size());
    }

    // ─── Equipment ───────────────────────────────────────────────

    @Test
    public void testEquipment_gettersAndSetters() {
        Equipment eq = new Equipment("E001", "Projector", true);
        assertEquals("E001", eq.getEquipmentId());
        assertEquals("Projector", eq.getEquipmentName());
        assertTrue(eq.isAvailable());

        eq.setAvailable(false);
        assertFalse(eq.isAvailable());
    }

    // ─── CateringService ─────────────────────────────────────────

    @Test
    public void testCateringService_getters() {
        CateringService catering = new CateringService("C001", "Coffee Break", 25.0);
        assertEquals("C001", catering.getCateringId());
        assertEquals("Coffee Break", catering.getServiceType());
        assertEquals(25.0, catering.getPrice());
    }

    // ─── Reservation Subclasses ──────────────────────────────────

    @Test
    public void testIndividualReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 5, "Small", Collections.emptyList());
        IndividualReservation r = new IndividualReservation("RES001", "2026-04-09", "10:00", 60, room);
        assertEquals("INDIVIDUAL", r.getType());
    }

    @Test
    public void testGroupReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 20, "Large", Collections.emptyList());
        GroupReservation r = new GroupReservation("RES002", "2026-04-09", "10:00", 60, room, 5);
        assertEquals("GROUP", r.getType());
        assertEquals(5, r.getParticipantCount());
    }

    @Test
    public void testVirtualReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Medium", Collections.emptyList());
        VirtualReservation r = new VirtualReservation("RES003", "2026-04-09", "10:00", 60, room);
        assertEquals("VIRTUAL", r.getType());
    }

    @Test
    public void testInPersonReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Medium", Collections.emptyList());
        InPersonReservation r = new InPersonReservation("RES004", "2026-04-09", "10:00", 60, room);
        assertEquals("IN_PERSON", r.getType());
    }

    @Test
    public void testUrgentReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 5, "Small", Collections.emptyList());
        UrgentReservation r = new UrgentReservation("RES005", "2026-04-09", "10:00", 30, room);
        assertEquals("URGENT", r.getType());
    }

    @Test
    public void testFollowUpReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 5, "Small", Collections.emptyList());
        FollowUpReservation r = new FollowUpReservation("RES006", "2026-04-09", "10:00", 30, room);
        assertEquals("FOLLOW_UP", r.getType());
    }

    @Test
    public void testAssessmentReservation_getType() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 5, "Small", Collections.emptyList());
        AssessmentReservation r = new AssessmentReservation("RES007", "2026-04-09", "10:00", 60, room);
        assertEquals("ASSESSMENT", r.getType());
    }

    @Test
    public void testReservation_settersAndGetters() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 5, "Small", Collections.emptyList());
        IndividualReservation r = new IndividualReservation("RES001", "2026-04-09", "10:00", 60, room);

        r.setDate("2026-05-01");
        r.setTime("14:00");
        r.setDuration(30);
        r.setStatus("Cancelled");
        r.setReservationType("INDIVIDUAL");

        assertEquals("2026-05-01", r.getDate());
        assertEquals("14:00", r.getTime());
        assertEquals(30, r.getDuration());
        assertEquals("Cancelled", r.getStatus());
        assertEquals("INDIVIDUAL", r.getReservationType());
        assertEquals(room, r.getRoom());
        assertEquals("RES001", r.getReservationId());
    }
}