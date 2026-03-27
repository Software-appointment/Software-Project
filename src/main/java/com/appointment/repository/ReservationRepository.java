package com.appointment.repository;

import com.appointment.domain.Reservation;
import java.util.LinkedList;
import java.util.List;

/**
 * Repository for managing reservations in memory.
 * Uses LinkedList as in-memory storage.
 *
 * @author Student C
 * @version 1.0
 */
public class ReservationRepository {

    /** In-memory list of all reservations. */
    private LinkedList<Reservation> reservations;

    /**
     * Constructor initializes empty reservations list.
     */
    public ReservationRepository() {
        this.reservations = new LinkedList<>();
    }

    /**
     * Saves a new reservation to the repository.
     *
     * @param reservation the reservation to save
     */
    public void save(Reservation reservation) {
        reservations.add(reservation);
    }

    /**
     * Returns all reservations in the repository.
     *
     * @return list of all reservations
     */
    public List<Reservation> findAll() {
        return reservations;
    }

    /**
     * Finds a reservation by its ID.
     *
     * @param reservationId the ID to search for
     * @return the reservation if found, null otherwise
     */
    public Reservation findById(String reservationId) {
        for (Reservation r : reservations) {
            if (r.getReservationId().equals(reservationId)) {
                return r;
            }
        }
        return null;
    }

    /**
     * Deletes a reservation by its ID.
     *
     * @param reservationId the ID of the reservation to delete
     */
    public void delete(String reservationId) {
        reservations.removeIf(r -> r.getReservationId().equals(reservationId));
    }
}