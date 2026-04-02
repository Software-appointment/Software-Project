package com.appointment.service;

import com.appointment.domain.Reservation;
import com.appointment.domain.User;
import com.appointment.repository.ReservationRepository;
import com.appointment.rules.BookingRuleStrategy;
import com.appointment.rules.CapacityRule;
import com.appointment.rules.DurationRule;
import com.appointment.rules.EquipmentAvailabilityRule;
import java.util.ArrayList;
import java.util.List;

/**
 * Service responsible for managing reservations.
 * Coordinates booking rules and notifications.
 *
 * @author Student B
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
     * Triggers a notification after successful booking.
     *
     * @param reservation the reservation to book
     * @param user the user making the reservation
     * @return the confirmed reservation
     */
    public Reservation book(Reservation reservation, User user) {
        List<BookingRuleStrategy> rules = new ArrayList<>();
        rules.add(new CapacityRule(reservation.getRoom().getCapacity()));
        rules.add(new DurationRule());
        rules.add(new EquipmentAvailabilityRule(reservation.getRoom().getAvailableEquipment()
                .stream()
                .map(e -> e.getEquipmentName())
                .collect(java.util.stream.Collectors.toList())));

        for (BookingRuleStrategy rule : rules) {
            if (!rule.isValid(reservation)) {
                throw new IllegalArgumentException(
                    "Booking rule failed: " + rule.getClass().getSimpleName()
                );
            }
        }

        reservation.setStatus("Confirmed");
        reservationRepository.save(reservation);

        // US3.1 - trigger notification after successful booking
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
}