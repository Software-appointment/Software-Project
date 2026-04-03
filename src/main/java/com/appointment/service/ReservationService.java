package com.appointment.service;

import com.appointment.domain.GroupReservation;
import com.appointment.domain.Reservation;
import com.appointment.domain.User;
import com.appointment.domain.VirtualReservation;
import com.appointment.repository.ReservationRepository;
import com.appointment.rules.BookingRuleStrategy;
import com.appointment.rules.CapacityRule;
import com.appointment.rules.DurationRule;
import com.appointment.rules.EquipmentAvailabilityRule;
import com.appointment.rules.GroupMinParticipantsRule;
import com.appointment.rules.VirtualEquipmentRule;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for managing reservations.
 * Coordinates booking rules, polymorphic dispatch, and notifications.
 *
 * @author Student C
 * @version 1.0
 */
public class ReservationService {

    /** Repository for storing reservations. */
    private ReservationRepository reservationRepository;

    /** Service for sending notifications. */
    private NotificationService notificationService;

    /**
     * Constructor to create ReservationService.
     *
     * @param reservationRepository the repository to store reservations
     * @param notificationService the service to send notifications
     */
    public ReservationService(ReservationRepository reservationRepository,
                              NotificationService notificationService) {
        this.reservationRepository = reservationRepository;
        this.notificationService   = notificationService;
    }

    /**
     * Books a reservation after validating all booking rules.
     * Applies type-specific rules based on reservation type (polymorphic dispatch).
     * Triggers a notification after successful booking.
     *
     * @param reservation the reservation to book
     * @param user the user making the reservation
     * @return the confirmed reservation
     */
    public Reservation book(Reservation reservation, User user) {
        List<BookingRuleStrategy> rules = new ArrayList<>();
        rules.add(new CapacityRule(reservation.getParticipantCount()));
        rules.add(new DurationRule());
        rules.add(new EquipmentAvailabilityRule(reservation.getRoom().getAvailableEquipment()
                .stream()
                .map(e -> e.getEquipmentName())
                .collect(java.util.stream.Collectors.toList())));

        // Polymorphic dispatch — apply type-specific rules
        if (reservation instanceof GroupReservation) {
            rules.add(new GroupMinParticipantsRule());
        } else if (reservation instanceof VirtualReservation) {
            rules.add(new VirtualEquipmentRule());
        }

        for (BookingRuleStrategy rule : rules) {
            if (!rule.isValid(reservation)) {
                throw new IllegalArgumentException(
                    "Booking rule failed: " + rule.getClass().getSimpleName()
                );
            }
        }

        reservation.setStatus("Confirmed");
        reservationRepository.save(reservation);

        notificationService.notifyObservers(user,
            "Reminder: Your reservation is confirmed for " + reservation.getDate()
            + " at " + reservation.getTime());

        return reservation;
    }

    /**
     * Returns all reservations in the system.
     *
     * @return list of all reservations
     */
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    /**
     * Modifies an existing future reservation.
     *
     * @param reservationId the ID of the reservation to modify
     * @param newDate new date (or null to keep existing)
     * @param newTime new time (or null to keep existing)
     * @param newDuration new duration in minutes (-1 to keep existing)
     * @return the updated reservation
     */
    public Reservation modify(String reservationId, String newDate,
                              String newTime, int newDuration) {
        Reservation reservation = reservationRepository.findById(reservationId);

        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found: " + reservationId);
        }

        if (reservation.getStatus().equals("Cancelled") ||
            reservation.getStatus().equals("Completed")) {
            throw new IllegalArgumentException(
                "Cannot modify a " + reservation.getStatus() + " reservation"
            );
        }

        if (newDate != null) reservation.setDate(newDate);
        if (newTime != null) reservation.setTime(newTime);
        if (newDuration != -1) reservation.setDuration(newDuration);

        reservationRepository.update(reservation);
        return reservation;
    }

    /**
     * Cancels an existing reservation and frees the time slot.
     *
     * @param reservationId the ID of the reservation to cancel
     * @param timeSlot the time slot to free after cancellation
     */
    public void cancel(String reservationId,
                       com.appointment.domain.TimeSlot timeSlot) {
        Reservation reservation = reservationRepository.findById(reservationId);

        if (reservation == null) {
            throw new IllegalArgumentException("Reservation not found: " + reservationId);
        }

        if (reservation.getStatus().equals("Cancelled")) {
            throw new IllegalArgumentException("Reservation is already cancelled");
        }

        if (reservation.getStatus().equals("Completed")) {
            throw new IllegalArgumentException("Cannot cancel a completed reservation");
        }

        reservation.setStatus("Cancelled");
        reservationRepository.update(reservation);
        timeSlot.setAvailable(true);
    }
}