package com.appointment.repository;

import com.appointment.domain.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * File-backed repository for storing and retrieving reservations.
 * Persists reservations to a text file so data survives application restarts.
 *
 * @author Student A
 * @version 1.0
 */
public class ReservationRepository {

    /** In-memory list of all reservations. */
    private LinkedList<Reservation> reservations;

    /** File path for persistent storage. */
    private static final String FILE_PATH = "reservations.txt";

    /**
     * Constructs a new ReservationRepository and loads data from file.
     */
    public ReservationRepository() {
        this.reservations = new LinkedList<>();
        loadFromFile();
    }

    /**
     * Saves a new reservation to the repository and persists to file.
     *
     * @param reservation the reservation to save
     */
    public void save(Reservation reservation) {
        reservations.add(reservation);
        saveToFile();
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
     * Deletes a reservation by its ID and persists to file.
     *
     * @param reservationId the ID of the reservation to delete
     * @return true if deleted, false if not found
     */
    public boolean delete(String reservationId) {
        Reservation toDelete = findById(reservationId);
        if (toDelete != null) {
            reservations.remove(toDelete);
            saveToFile();
            return true;
        }
        return false;
    }

    /**
     * Updates an existing reservation and persists to file.
     *
     * @param updatedReservation the updated reservation object
     * @return true if updated, false if not found
     */
    public boolean update(Reservation updatedReservation) {
        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getReservationId()
                    .equals(updatedReservation.getReservationId())) {
                reservations.set(i, updatedReservation);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    /**
     * Saves all reservations to the text file.
     * Format: id|date|time|duration|roomName|type|status
     */
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Reservation r : reservations) {
                writer.write(
                    r.getReservationId() + "|" +
                    r.getDate() + "|" +
                    r.getTime() + "|" +
                    r.getDuration() + "|" +
                    r.getRoom().getRoomName() + "|" +
                    r.getType() + "|" +
                    r.getStatus()
                );
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving reservations: " + e.getMessage());
        }
    }

    /**
     * Loads all reservations from the text file on startup.
     */
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 7) continue;

                String id       = parts[0];
                String date     = parts[1];
                String time     = parts[2];
                int duration    = Integer.parseInt(parts[3]);
                String roomName = parts[4];
                String type     = parts[5];
                String status   = parts[6];

                List<Equipment> equipment = new java.util.ArrayList<>();
                if (roomName.equals("Virtual Room")) {
                    equipment.add(new Equipment("E2", "Video Conference System", true));
                }
                MeetingRoom room = new MeetingRoom("R1", roomName, 10, "Medium", equipment);

                Reservation reservation;
                switch (type.toUpperCase()) {
                    case "GROUP":      reservation = new GroupReservation(id, date, time, duration, room, 2); break;
                    case "VIRTUAL":    reservation = new VirtualReservation(id, date, time, duration, room); break;
                    case "INPERSON":
                    case "IN_PERSON":  reservation = new InPersonReservation(id, date, time, duration, room); break;
                    case "URGENT":     reservation = new UrgentReservation(id, date, time, duration, room); break;
                    case "FOLLOWUP":
                    case "FOLLOW_UP":  reservation = new FollowUpReservation(id, date, time, duration, room); break;
                    case "ASSESSMENT": reservation = new AssessmentReservation(id, date, time, duration, room); break;
                    default:           reservation = new IndividualReservation(id, date, time, duration, room); break;
                }
                reservation.setStatus(status);
                reservations.add(reservation);
            }
        } catch (IOException e) {
            System.err.println("Error loading reservations: " + e.getMessage());
        }
    }
}