package com.appointment.repository;

import com.appointment.domain.Reservation;
import java.util.LinkedList;
import java.util.List;

/**
 * In-memory repository for storing and retrieving reservations.
 *
 * @author Student A
 * @version 1.0
 */
public class ReservationRepository {

    /** In-memory list of all reservations. */
    private LinkedList<Reservation> reservations;

    /**
     * Constructs a new empty ReservationRepository.
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
     * Finds a reservation by its ID.
     *
     * @param reservationId the ID to search for
     * @return the matching Reservation, or null if not found
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
     * Returns all reservations in the system.
     *
     * @return list of all reservations
     */
    public List<Reservation> findAll() {
        return reservations;
    }

    /**
     * Deletes a reservation by its ID.
     *
     * @param reservationId the ID of the reservation to delete
     * @return true if deleted, false if not found
     */
    public boolean delete(String reservationId) {
        Reservation toDelete = findById(reservationId);
        if (toDelete != null) {
            reservations.remove(toDelete);
            return true;
        }
        return false;
    }

    /**
     * Updates an existing reservation.
     *
     * @param updatedReservation the updated reservation object
     * @return true if updated, false if not found
     */
    public boolean update(Reservation updatedReservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId()
                    .equals(updatedReservation.getReservationId())) {
                reservations.set(i, updatedReservation);
                return true;
            }
        }
        return false;
    }
}