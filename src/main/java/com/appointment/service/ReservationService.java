package com.appointment.service;

import com.appointment.domain.Reservation;
import com.appointment.repository.ReservationRepository;
import com.appointment.rules.BookingRuleStrategy;
import com.appointment.rules.CapacityRule;
import com.appointment.rules.DurationRule;
import com.appointment.rules.EquipmentAvailabilityRule;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {

    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation book(Reservation reservation) {
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
        return reservation;
    }

    public List<Reservation> getAll() {
    	return reservationRepository.findAll();
    }
}