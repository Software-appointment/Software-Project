package com.appointment.service;

import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;
import com.appointment.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationServiceTest {

    private ReservationService reservationService;
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        reservationRepository = new ReservationRepository();
        reservationService = new ReservationService(reservationRepository);
    }

    @Test
    void testBookSuccess() {
        MeetingRoom room = new MeetingRoom("R1", "Small Room", 10, "Small", new ArrayList<>());
        Reservation reservation = new Reservation("RES1", "2026-04-10", "10:00", 60, room) {
            @Override
            public String getType() { return "INDIVIDUAL"; }
        };
        Reservation result = reservationService.book(reservation);
        assertEquals("Confirmed", result.getStatus());
    }

    @Test
    void testBookFailInvalidDuration() {
        MeetingRoom room = new MeetingRoom("R1", "Small Room", 10, "Small", new ArrayList<>());
        Reservation reservation = new Reservation("RES2", "2026-04-10", "10:00", 45, room) {
            @Override
            public String getType() { return "INDIVIDUAL"; }
        };
        assertThrows(IllegalArgumentException.class, () -> reservationService.book(reservation));
    }
}