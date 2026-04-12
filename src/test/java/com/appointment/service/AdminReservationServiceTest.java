package com.appointment.service;
import com.appointment.domain.Administrator;
import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;
import com.appointment.repository.ReservationRepository;
import com.appointment.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for AdminReservationService (US4.2).
 * Verifies that only logged-in administrators can modify or cancel reservations.
 *
 * @author Student A
 * @version 1.0
 */
public class AdminReservationServiceTest {
    private AdminReservationService adminService;
    private ReservationRepository reservationRepository;
    private UserRepository userRepository;
    private Administrator admin;
    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        reservationRepository = new ReservationRepository();
        userRepository        = new UserRepository();
        adminService          = new AdminReservationService(
                                    reservationRepository, userRepository);
        admin = new Administrator("A001", "admin", "1234");
        userRepository.addAdministrator(admin);
        MeetingRoom room = new MeetingRoom("R1", "Small Room", 10, "Small",
                Collections.emptyList());
        reservation = new Reservation("RES001", "2026-04-10", "10:00", 60, room) {
            @Override public String getType() { return "TEST"; }
        };
        reservationRepository.save(reservation);
    }

    // ─── Cancel Tests ────────────────────────────────────────────

    @Test
    public void testCancel_whenAdminLoggedIn_success() {
        admin.setLoggedIn(true);
        adminService.cancelReservation("RES001", "admin", "test@test.com", "TestUser");
        assertEquals("Cancelled", reservation.getStatus(),
                "Reservation should be cancelled by admin");
    }

    @Test
    public void testCancel_whenAdminNotLoggedIn_throwsException() {
        admin.setLoggedIn(false);
        assertThrows(IllegalStateException.class, () ->
            adminService.cancelReservation("RES001", "admin", "test@test.com", "TestUser"),
            "Should throw when admin is not logged in"
        );
    }

    @Test
    public void testCancel_whenReservationNotFound_throwsException() {
        admin.setLoggedIn(true);
        assertThrows(IllegalArgumentException.class, () ->
            adminService.cancelReservation("INVALID", "admin", "test@test.com", "TestUser"),
            "Should throw when reservation not found"
        );
    }

    // ─── Modify Tests ────────────────────────────────────────────

    @Test
    public void testModify_whenAdminLoggedIn_success() {
        admin.setLoggedIn(true);
        adminService.modifyReservation("RES001", "admin", "2026-05-01", "14:00",
                "test@test.com", "TestUser");
        assertEquals("2026-05-01", reservation.getDate(), "Date should be updated");
        assertEquals("14:00", reservation.getTime(), "Time should be updated");
    }

    @Test
    public void testModify_whenAdminNotLoggedIn_throwsException() {
        admin.setLoggedIn(false);
        assertThrows(IllegalStateException.class, () ->
            adminService.modifyReservation("RES001", "admin", "2026-05-01", "14:00",
                "test@test.com", "TestUser"),
            "Should throw when admin is not logged in"
        );
    }

    @Test
    public void testModify_whenReservationNotFound_throwsException() {
        admin.setLoggedIn(true);
        assertThrows(IllegalArgumentException.class, () ->
            adminService.modifyReservation("INVALID", "admin", "2026-05-01", "14:00",
                "test@test.com", "TestUser"),
            "Should throw when reservation not found"
        );
    }
}