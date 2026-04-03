package com.appointment.service;

import com.appointment.domain.*;
import com.appointment.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests that the same book() method handles all 7 reservation types correctly.
 *
 * @author Student C
 * @version 1.0
 */
public class PolymorphismTest {

    private ReservationService reservationService;
    private User user;
    private MeetingRoom room;

    @BeforeEach
    void setUp() {
        ReservationRepository repository = new ReservationRepository();
        NotificationService notificationService = new NotificationService();
        reservationService = new ReservationService(repository, notificationService);
        user = new User("U1", "Test User", "test@test.com", "0599000000");
        room = new MeetingRoom("R1", "Small Room", 10, "Small", new ArrayList<>());
    }

    @Test
    void testBookIndividualReservation() {
        Reservation r = new IndividualReservation("RES1", "2026-05-01", "10:00", 60, room);
        assertEquals("Confirmed", reservationService.book(r, user).getStatus());
    }

    @Test
    void testBookFollowUpReservation() {
        Reservation r = new FollowUpReservation("RES2", "2026-05-01", "10:00", 60, room);
        assertEquals("Confirmed", reservationService.book(r, user).getStatus());
    }

    @Test
    void testBookAssessmentReservation() {
        Reservation r = new AssessmentReservation("RES3", "2026-05-01", "10:00", 60, room);
        assertEquals("Confirmed", reservationService.book(r, user).getStatus());
    }

    @Test
    void testBookUrgentReservation() {
        Reservation r = new UrgentReservation("RES4", "2026-05-01", "10:00", 60, room);
        assertEquals("Confirmed", reservationService.book(r, user).getStatus());
    }

    @Test
    void testBookInPersonReservation() {
        Reservation r = new InPersonReservation("RES5", "2026-05-01", "10:00", 60, room);
        assertEquals("Confirmed", reservationService.book(r, user).getStatus());
    }

    @Test
    void testBookGroupReservation() {
        Reservation r = new GroupReservation("RES6", "2026-05-01", "10:00", 60, room, 3);
        assertEquals("Confirmed", reservationService.book(r, user).getStatus());
    }

    @Test
    void testBookVirtualReservation() {
        Equipment videoConf = new Equipment("E1", "Video Conference System", true);
        java.util.List<Equipment> equipment = new ArrayList<>();
        equipment.add(videoConf);
        MeetingRoom virtualRoom = new MeetingRoom("R2", "Virtual Room", 10, "Medium", equipment);
        Reservation r = new VirtualReservation("RES7", "2026-05-01", "10:00", 60, virtualRoom);
        assertEquals("Confirmed", reservationService.book(r, user).getStatus());
    }
}