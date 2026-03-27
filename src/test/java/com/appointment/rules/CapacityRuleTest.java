package com.appointment.rules;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for CapacityRule (US2.3).
 *
 * @author Student A
 * @version 1.0
 */
public class CapacityRuleTest {

    private MeetingRoom smallRoom;
    private MeetingRoom largeRoom;

    @BeforeEach
    public void setUp() {
        // Small room: capacity 5
        smallRoom = new MeetingRoom("R1", "Small Room", 5, "Small", Collections.emptyList());
        // Large room: capacity 20
        largeRoom = new MeetingRoom("R2", "Large Room", 20, "Large", Collections.emptyList());
    }

    @Test
    public void testCapacityValid_whenParticipantsWithinLimit() {
        // 5 participants, room capacity 5 → should pass
        CapacityRule rule = new CapacityRule(5);
        Reservation reservation = new TestReservation("RES001", "2026-03-20", "10:00", 60, smallRoom);
        assertTrue(rule.isValid(reservation), "Should pass when participants <= capacity");
    }

    @Test
    public void testCapacityInvalid_whenParticipantsExceedLimit() {
        // 10 participants, room capacity 5 → should fail
        CapacityRule rule = new CapacityRule(10);
        Reservation reservation = new TestReservation("RES002", "2026-03-20", "10:00", 60, smallRoom);
        assertFalse(rule.isValid(reservation), "Should fail when participants > capacity");
    }

    @Test
    public void testCapacityValid_whenLargeRoom() {
        // 15 participants, room capacity 20 → should pass
        CapacityRule rule = new CapacityRule(15);
        Reservation reservation = new TestReservation("RES003", "2026-03-20", "10:00", 60, largeRoom);
        assertTrue(rule.isValid(reservation), "Should pass when room has enough capacity");
    }

    @Test
    public void testCapacityInvalid_whenExactlyOverLimit() {
        // 21 participants, room capacity 20 → should fail
        CapacityRule rule = new CapacityRule(21);
        Reservation reservation = new TestReservation("RES004", "2026-03-20", "10:00", 60, largeRoom);
        assertFalse(rule.isValid(reservation), "Should fail when participants exceed capacity by 1");
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