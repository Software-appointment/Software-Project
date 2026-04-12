package com.appointment.service;

import com.appointment.domain.Administrator;
import com.appointment.domain.Reservation;
import com.appointment.repository.ReservationRepository;
import com.appointment.repository.UserRepository;

/**
 * Service for administrator-only reservation management.
 * Handles cancel and modify actions with session check.
 *
 * @author Student C
 * @version 1.0
 */
public class AdminReservationService {

    private ReservationRepository reservationRepository;
    private UserRepository userRepository;

    /**
     * Constructor to create AdminReservationService.
     *
     * @param reservationRepository the reservation repository
     * @param userRepository the user repository
     */
    public AdminReservationService(ReservationRepository reservationRepository,
                                   UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository        = userRepository;
    }

    /**
     * Cancels a reservation. Only logged-in administrators can perform this action.
     *
     * @param reservationId the ID of the reservation to cancel
     * @param adminUsername the username of the administrator
     */
    public void cancelReservation(String reservationId, String adminUsername) {
        checkAdminSession(adminUsername);
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found: " + reservationId);
        }
        reservation.setStatus("Cancelled");
        reservationRepository.update(reservation);
    }

    /**
     * Modifies a reservation. Only logged-in administrators can perform this action.
     *
     * @param reservationId the ID of the reservation to modify
     * @param adminUsername the username of the administrator
     * @param newDate the new date for the reservation
     * @param newTime the new time for the reservation
     */
    public void modifyReservation(String reservationId, String adminUsername,
                                  String newDate, String newTime) {
        checkAdminSession(adminUsername);
        Reservation reservation = reservationRepository.findById(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found: " + reservationId);
        }
        if (newDate != null && !newDate.isEmpty()) {
            reservation.setDate(newDate);
        }
        if (newTime != null && !newTime.isEmpty()) {
            reservation.setTime(newTime);
        }
        reservationRepository.update(reservation);
    }

    /**
     * Checks if the administrator is logged in.
     *
     * @param adminUsername the username to check
     */
    private void checkAdminSession(String adminUsername) {
        Administrator admin = userRepository.findAdminByUsername(adminUsername);
        if (admin == null || !admin.isLoggedIn()) {
            throw new IllegalStateException("Admin not logged in: " + adminUsername);
        }
    }
}