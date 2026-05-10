package com.appointment.repository;

import com.appointment.domain.AssessmentReservation;
import com.appointment.domain.Equipment;
import com.appointment.domain.FollowUpReservation;
import com.appointment.domain.GroupReservation;
import com.appointment.domain.IndividualReservation;
import com.appointment.domain.InPersonReservation;
import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;
import com.appointment.domain.UrgentReservation;
import com.appointment.domain.VirtualReservation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * File-backed repository for storing and retrieving reservations.
 * Persists reservations to a text file so data survives application restarts.
 *
 * @author Student A
 * @version 1.0
 */
public class ReservationRepository {

    private static final Logger LOGGER =
            Logger.getLogger(ReservationRepository.class.getName());

    /** File path for persistent storage. */
    private static final String FILE_PATH = "reservations.txt";

    /** In-memory list of all reservations. */
    private LinkedList<Reservation> reservations;

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
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId().equals(reservationId)) {
                return reservation;
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
     * Format: id|date|time|duration|roomName|type|status|userName|userEmail
     */
    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Reservation reservation : reservations) {
                writer.write(
                        reservation.getReservationId() + "|"
                                + reservation.getDate() + "|"
                                + reservation.getTime() + "|"
                                + reservation.getDuration() + "|"
                                + reservation.getRoom().getRoomName() + "|"
                                + reservation.getType() + "|"
                                + reservation.getStatus() + "|"
                                + getSafeText(reservation.getUserName()) + "|"
                                + getSafeText(reservation.getUserEmail())
                );
                writer.newLine();
            }
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error saving reservations", exception);
        }
    }

    /**
     * Loads all reservations from the text file on startup.
     */
    private void loadFromFile() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = reader.readLine()) != null) {
                loadReservationLine(line);
            }
        } catch (IOException exception) {
            LOGGER.log(Level.SEVERE, "Error loading reservations", exception);
        }
    }

    /**
     * Loads one reservation line from the file.
     *
     * @param line reservation line from file
     */
    private void loadReservationLine(String line) {
        String[] parts = line.split("\\|");

        if (parts.length < 7) {
            return;
        }

        String id = parts[0];
        String date = parts[1];
        String time = parts[2];
        int duration = Integer.parseInt(parts[3]);
        String roomName = parts[4];
        String type = parts[5];
        String status = parts[6];
        String userName = parts.length > 7 ? parts[7] : "-";
        String userEmail = parts.length > 8 ? parts[8] : "-";

        MeetingRoom room = createRoom(roomName);
        Reservation reservation = createReservation(type, id, date, time, duration, room);

        reservation.setStatus(status);
        reservation.setUserName(userName);
        reservation.setUserEmail(userEmail);

        reservations.add(reservation);
    }

    /**
     * Creates a meeting room object while loading from file.
     *
     * @param roomName room name
     * @return meeting room
     */
    private MeetingRoom createRoom(String roomName) {
        List<Equipment> equipment = new java.util.ArrayList<>();

        if ("Virtual Room".equals(roomName)) {
            equipment.add(new Equipment("E2", "Video Conference System", true));
        }

        return new MeetingRoom("R1", roomName, 10, "Medium", equipment);
    }

    /**
     * Creates the correct reservation subtype.
     *
     * @param type reservation type
     * @param id reservation id
     * @param date reservation date
     * @param time reservation time
     * @param duration reservation duration
     * @param room meeting room
     * @return reservation object
     */
    private Reservation createReservation(String type, String id, String date,
                                          String time, int duration,
                                          MeetingRoom room) {
        switch (type.toUpperCase()) {
            case "GROUP":
                return new GroupReservation(id, date, time, duration, room, 2);
            case "VIRTUAL":
                return new VirtualReservation(id, date, time, duration, room);
            case "INPERSON":
            case "IN_PERSON":
                return new InPersonReservation(id, date, time, duration, room);
            case "URGENT":
                return new UrgentReservation(id, date, time, duration, room);
            case "FOLLOWUP":
            case "FOLLOW_UP":
                return new FollowUpReservation(id, date, time, duration, room);
            case "ASSESSMENT":
                return new AssessmentReservation(id, date, time, duration, room);
            default:
                return new IndividualReservation(id, date, time, duration, room);
        }
    }

    /**
     * Returns a safe text value for file saving.
     *
     * @param value text value
     * @return value or dash if null
     */
    private String getSafeText(String value) {
        return value != null ? value : "-";
    }
}
