package com.appointment.service;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;
import com.appointment.domain.TimeSlot;
import com.appointment.observer.Observer;
import com.appointment.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;


public class ModifyCancelTest {

    private ReservationService reservationService;
    private ReservationRepository reservationRepository;
    private NotificationService notificationService;
    private MeetingRoom room;
    private TimeSlot timeSlot;

    @BeforeEach
    public void setUp() {
        reservationRepository = new ReservationRepository();
        notificationService   = new NotificationService();
        reservationService    = new ReservationService(reservationRepository, notificationService);

        room     = new MeetingRoom("R1", "Small Room", 10, "Small", Collections.emptyList());
        timeSlot = new TimeSlot("S1", "10:00", "11:00");
    }

    
    private Reservation makeReservation(String id) {
        return new Reservation(id, "2026-04-10", "10:00", 60, room) {
            @Override public String getType() { return "TEST"; }
        };
    }

    // ─── Modify Tests ────────────────────────────────────────────

    @Test
    public void testModify_changesDate() {
        Reservation r = makeReservation("R001");
        reservationRepository.save(r);

        reservationService.modify("R001", "2026-05-01", null, -1);

        assertEquals("2026-05-01", r.getDate(), "Date should be updated");
    }

    @Test
    public void testModify_changesTime() {
        Reservation r = makeReservation("R002");
        reservationRepository.save(r);

        reservationService.modify("R002", null, "14:00", -1);

        assertEquals("14:00", r.getTime(), "Time should be updated");
    }

    @Test
    public void testModify_changesDuration() {
        Reservation r = makeReservation("R003");
        reservationRepository.save(r);

        reservationService.modify("R003", null, null, 30);

        assertEquals(30, r.getDuration(), "Duration should be updated");
    }

    @Test
    public void testModify_throwsException_whenNotFound() {
        assertThrows(IllegalArgumentException.class, () ->
            reservationService.modify("INVALID", "2026-05-01", null, -1)
        );
    }

    @Test
    public void testModify_throwsException_whenCancelled() {
        Reservation r = makeReservation("R004");
        r.setStatus("Cancelled");
        reservationRepository.save(r);

        assertThrows(IllegalArgumentException.class, () ->
            reservationService.modify("R004", "2026-05-01", null, -1)
        );
    }

    // ─── Cancel Tests ────────────────────────────────────────────

    @Test
    public void testCancel_setsStatusCancelled() {
        Reservation r = makeReservation("R005");
        reservationRepository.save(r);

        reservationService.cancel("R005", timeSlot);

        assertEquals("Cancelled", r.getStatus(), "Status should be Cancelled");
    }

    @Test
    public void testCancel_freesTimeSlot() {
        Reservation r = makeReservation("R006");
        reservationRepository.save(r);
        timeSlot.setAvailable(false);

        reservationService.cancel("R006", timeSlot);

        assertTrue(timeSlot.isAvailable(), "TimeSlot should be available after cancellation");
    }

    @Test
    public void testCancel_throwsException_whenNotFound() {
        assertThrows(IllegalArgumentException.class, () ->
            reservationService.cancel("INVALID", timeSlot)
        );
    }

    @Test
    public void testCancel_throwsException_whenAlreadyCancelled() {
        Reservation r = makeReservation("R007");
        r.setStatus("Cancelled");
        reservationRepository.save(r);

        assertThrows(IllegalArgumentException.class, () ->
            reservationService.cancel("R007", timeSlot)
        );
    }
}