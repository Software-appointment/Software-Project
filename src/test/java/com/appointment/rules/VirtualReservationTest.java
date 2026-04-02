package com.appointment.rules;

import com.appointment.domain.Equipment;
import com.appointment.domain.MeetingRoom;
import com.appointment.domain.VirtualReservation;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for VirtualEquipmentRule (US5.2).
 *
 * @author Student B
 * @version 1.0
 */
public class VirtualReservationTest {

    /**
     * Tests that a virtual reservation with Video Conference System passes.
     */
    @Test
    void testVirtualWithVideoConference_passes() {
        Equipment videoConference = new Equipment("E1", "Video Conference System", true);
        List<Equipment> equipment = new ArrayList<>();
        equipment.add(videoConference);
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Medium", equipment);
        VirtualReservation reservation = new VirtualReservation("V1", "2026-04-10", "10:00", 60, room);
        VirtualEquipmentRule rule = new VirtualEquipmentRule();
        assertTrue(rule.isValid(reservation));
    }

    /**
     * Tests that a virtual reservation without Video Conference System fails.
     */
    @Test
    void testVirtualWithoutVideoConference_fails() {
        MeetingRoom room = new MeetingRoom("R1", "Room", 10, "Medium", new ArrayList<>());
        VirtualReservation reservation = new VirtualReservation("V2", "2026-04-10", "10:00", 60, room);
        VirtualEquipmentRule rule = new VirtualEquipmentRule();
        assertFalse(rule.isValid(reservation));
    }
}