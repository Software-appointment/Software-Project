package com.appointment.rules;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;


public class DurationRuleTest {

    private DurationRule rule;
    private MeetingRoom room;

    @BeforeEach
    public void setUp() {
        rule = new DurationRule();
        room = new MeetingRoom("R1", "Small Room", 10, "Small", Collections.emptyList());
    }

    @Test
    public void testDurationValid_30Minutes() {
        Reservation reservation = new TestReservation("R001", "2026-03-20", "10:00", 30, room);
        assertTrue(rule.isValid(reservation), "30 minutes should be valid");
    }

    @Test
    public void testDurationValid_60Minutes() {
        Reservation reservation = new TestReservation("R002", "2026-03-20", "10:00", 60, room);
        assertTrue(rule.isValid(reservation), "60 minutes should be valid");
    }

    @Test
    public void testDurationValid_120Minutes() {
        Reservation reservation = new TestReservation("R003", "2026-03-20", "10:00", 120, room);
        assertTrue(rule.isValid(reservation), "120 minutes should be valid");
    }

    @Test
    public void testDurationInvalid_45Minutes() {
        Reservation reservation = new TestReservation("R004", "2026-03-20", "10:00", 45, room);
        assertFalse(rule.isValid(reservation), "45 minutes should be invalid");
    }

    @Test
    public void testDurationInvalid_90Minutes() {
        Reservation reservation = new TestReservation("R005", "2026-03-20", "10:00", 90, room);
        assertFalse(rule.isValid(reservation), "90 minutes should be invalid");
    }

    @Test
    public void testDurationInvalid_0Minutes() {
        Reservation reservation = new TestReservation("R006", "2026-03-20", "10:00", 0, room);
        assertFalse(rule.isValid(reservation), "0 minutes should be invalid");
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