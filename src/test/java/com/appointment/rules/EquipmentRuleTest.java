package com.appointment.rules;

import com.appointment.domain.Equipment;
import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for EquipmentAvailabilityRule (US2.4).
 *
 * @author Student A
 * @version 1.0
 */
public class EquipmentRuleTest {

    private Equipment projector;
    private Equipment whiteboard;
    private MeetingRoom roomWithEquipment;
    private MeetingRoom roomWithoutEquipment;

    @BeforeEach
    public void setUp() {
        projector  = new Equipment("E1", "Projector", true);
        whiteboard = new Equipment("E2", "Whiteboard", true);

        roomWithEquipment = new MeetingRoom("R1", "Large Room", 20, "Large",
                Arrays.asList(projector, whiteboard));

        roomWithoutEquipment = new MeetingRoom("R2", "Small Room", 5, "Small",
                Collections.emptyList());
    }

    @Test
    public void testEquipmentValid_whenRoomHasAllRequested() {
        List<String> requested = Arrays.asList("Projector", "Whiteboard");
        EquipmentAvailabilityRule rule = new EquipmentAvailabilityRule(requested);
        Reservation reservation = new TestReservation("R001", "2026-03-20", "10:00", 60, roomWithEquipment);
        assertTrue(rule.isValid(reservation), "Should pass when room has all requested equipment");
    }

    @Test
    public void testEquipmentInvalid_whenRoomMissingEquipment() {
        List<String> requested = Arrays.asList("Projector");
        EquipmentAvailabilityRule rule = new EquipmentAvailabilityRule(requested);
        Reservation reservation = new TestReservation("R002", "2026-03-20", "10:00", 60, roomWithoutEquipment);
        assertFalse(rule.isValid(reservation), "Should fail when room has no equipment");
    }

    @Test
    public void testEquipmentValid_whenNoEquipmentRequested() {
        List<String> requested = Collections.emptyList();
        EquipmentAvailabilityRule rule = new EquipmentAvailabilityRule(requested);
        Reservation reservation = new TestReservation("R003", "2026-03-20", "10:00", 60, roomWithoutEquipment);
        assertTrue(rule.isValid(reservation), "Should pass when no equipment is requested");
    }

    @Test
    public void testEquipmentInvalid_whenOnlyPartialEquipmentAvailable() {
        List<String> requested = Arrays.asList("Projector", "Video Conference System");
        EquipmentAvailabilityRule rule = new EquipmentAvailabilityRule(requested);
        Reservation reservation = new TestReservation("R004", "2026-03-20", "10:00", 60, roomWithEquipment);
        assertFalse(rule.isValid(reservation), "Should fail when some equipment is missing");
    }

    /**
     * Concrete subclass of Reservation used only for testing purposes.
     */
    private static class TestReservation extends Reservation {

        public TestReservation(String id, String date, String time, int duration, MeetingRoom room) {
            super(id, date, time, duration, room);
        }

        @Override
        public String getType() {
            return "TEST";
        }
    }
}