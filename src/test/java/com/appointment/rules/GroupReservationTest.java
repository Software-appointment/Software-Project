package com.appointment.rules;

import com.appointment.domain.GroupReservation;
import com.appointment.domain.MeetingRoom;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for GroupMinParticipantsRule (US5.2).
 *
 * @author Student B
 * @version 1.0
 */
public class GroupReservationTest {

    /**
     * Tests that a group reservation with 3 participants passes the rule.
     */
    @Test
    void testGroupWithEnoughParticipants_passes() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Medium", new ArrayList<>());
        GroupReservation reservation = new GroupReservation("G1", "2026-04-10", "10:00", 60, room, 3);
        GroupMinParticipantsRule rule = new GroupMinParticipantsRule();
        assertTrue(rule.isValid(reservation));
    }

    /**
     * Tests that a group reservation with only 1 participant fails the rule.
     */
    @Test
    void testGroupWithOneParticipant_fails() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Medium", new ArrayList<>());
        GroupReservation reservation = new GroupReservation("G2", "2026-04-10", "10:00", 60, room, 1);
        GroupMinParticipantsRule rule = new GroupMinParticipantsRule();
        assertFalse(rule.isValid(reservation));
    }
}