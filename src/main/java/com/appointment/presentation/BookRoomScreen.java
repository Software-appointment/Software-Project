package com.appointment.presentation;

import com.appointment.domain.AssessmentReservation;
import com.appointment.domain.Equipment;
import com.appointment.domain.FollowUpReservation;
import com.appointment.domain.GroupReservation;
import com.appointment.domain.IndividualReservation;
import com.appointment.domain.InPersonReservation;
import com.appointment.domain.MeetingRoom;
import com.appointment.domain.Reservation;
import com.appointment.domain.UrgentReservation;
import com.appointment.domain.User;
import com.appointment.domain.VirtualReservation;
import com.appointment.service.ReservationService;
import com.appointment.service.RoomService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

/**
 * Screen for booking a meeting room.
 *
 * @author Student B
 * @version 1.0
 */
public class BookRoomScreen extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color BLUE      = new Color(66, 165, 245);
    private static final Color YELLOW    = new Color(253, 216, 53);
    private static final Color GREEN     = new Color(102, 187, 106);
    private static final Color BG        = new Color(248, 251, 255);
    private static final Color DARK_BLUE = new Color(13, 71, 161);
    private static final Color SUCCESS_GREEN = new Color(27, 94, 32);
    private static final Color ERROR_PINK = new Color(136, 14, 79);
    private static final Color READ_ONLY_GREEN = new Color(232, 245, 233);
    private static final Color CATERING_BG = new Color(255, 248, 225);

    private transient ReservationService reservationService;
    private transient RoomService roomService;

    private String username;
    private JTextField userNameField;
    private JTextField emailField;
    private JTextField dateField;
    private JTextField timeField;
    private JTextField durationField;
    private JComboBox<String> typeCombo;
    private JComboBox<String> roomCombo;
    private JCheckBox cateringCheckBox;
    private JComboBox<String> cateringCombo;
    private JLabel statusLabel;

    /**
     * Constructor for BookRoomScreen.
     *
     * @param reservationService the reservation service
     * @param roomService the room service
     * @param username the logged-in user's name
     */
    public BookRoomScreen(ReservationService reservationService,
                          RoomService roomService,
                          String username) {
        this.reservationService = reservationService;
        this.roomService = roomService;
        this.username = username;

        setBackground(BG);
        setLayout(new BorderLayout(10, 10));
        buildUI();
    }

    /**
     * Builds the UI components.
     */
    private void buildUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLUE);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("📅 Book a Meeting Room");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BG);
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        addLabel(form, gbc, "📝 User Name:");

        gbc.gridx = 1;
        userNameField = new JTextField(18);
        userNameField.setText(username);
        userNameField.setEditable(false);
        userNameField.setBackground(READ_ONLY_GREEN);
        styleField(userNameField);
        form.add(userNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        addLabel(form, gbc, "📧 Email:");

        gbc.gridx = 1;
        emailField = new JTextField(18);
        styleField(emailField);
        form.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addLabel(form, gbc, "📅 Date (yyyy-MM-dd):");

        gbc.gridx = 1;
        dateField = new JTextField(18);
        styleField(dateField);
        form.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addLabel(form, gbc, "⏰ Time (HH:mm):");

        gbc.gridx = 1;
        timeField = new JTextField(18);
        styleField(timeField);
        form.add(timeField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        addLabel(form, gbc, "⏱️ Duration (30/60/120 min):");

        gbc.gridx = 1;
        durationField = new JTextField(18);
        styleField(durationField);
        form.add(durationField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        addLabel(form, gbc, "📋 Reservation Type:");

        gbc.gridx = 1;
        typeCombo = new JComboBox<>(new String[] {
            "Individual", "Group", "Virtual",
            "InPerson", "Urgent", "FollowUp", "Assessment"
        });
        typeCombo.setBackground(Color.WHITE);
        typeCombo.setForeground(DARK_BLUE);
        typeCombo.setFont(new Font("Arial", Font.PLAIN, 13));
        form.add(typeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        addLabel(form, gbc, "🏠 Room:");

        gbc.gridx = 1;
        roomCombo = new JComboBox<>(new String[] {
            "Small Room", "Medium Room", "Large Conference", "Virtual Room"
        });
        roomCombo.setBackground(Color.WHITE);
        roomCombo.setForeground(DARK_BLUE);
        roomCombo.setFont(new Font("Arial", Font.PLAIN, 13));
        form.add(roomCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        addLabel(form, gbc, "🍽️ Catering Service:");

        gbc.gridx = 1;
        cateringCheckBox = new JCheckBox("Request Catering");
        cateringCheckBox.setBackground(BG);
        cateringCheckBox.setForeground(DARK_BLUE);
        cateringCheckBox.setFont(new Font("Arial", Font.BOLD, 13));
        form.add(cateringCheckBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        addLabel(form, gbc, "🍴 Catering Type:");

        gbc.gridx = 1;
        cateringCombo = new JComboBox<>(new String[] {
            "Coffee Break - $10",
            "Lunch - $25",
            "Full Catering - $50"
        });
        cateringCombo.setBackground(CATERING_BG);
        cateringCombo.setForeground(DARK_BLUE);
        cateringCombo.setFont(new Font("Arial", Font.PLAIN, 13));
        cateringCombo.setEnabled(false);
        form.add(cateringCombo, gbc);

        cateringCheckBox.addActionListener(event ->
                cateringCombo.setEnabled(cateringCheckBox.isSelected())
        );

        add(form, BorderLayout.CENTER);

        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(BG);

        JButton bookBtn = new JButton("✅ Confirm Booking");
        bookBtn.setBackground(GREEN);
        bookBtn.setForeground(SUCCESS_GREEN);
        bookBtn.setFont(new Font("Arial", Font.BOLD, 13));
        bookBtn.setFocusPainted(false);
        bookBtn.setBorderPainted(false);
        bookBtn.addActionListener(event -> handleBook());

        JButton clearBtn = new JButton("🔄 Clear");
        clearBtn.setBackground(BLUE);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 13));
        clearBtn.setFocusPainted(false);
        clearBtn.setBorderPainted(false);
        clearBtn.addActionListener(event -> clearFields());

        btnPanel.add(bookBtn);
        btnPanel.add(clearBtn);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(BG);
        southPanel.add(statusLabel, BorderLayout.NORTH);
        southPanel.add(btnPanel, BorderLayout.CENTER);

        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds a styled label to the form.
     *
     * @param panel the panel to add the label to
     * @param gbc layout constraints
     * @param text label text
     */
    private void addLabel(JPanel panel, GridBagConstraints gbc, String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(DARK_BLUE);
        panel.add(label, gbc);
    }

    /**
     * Styles a text field.
     *
     * @param field the text field to style
     */
    private void styleField(JTextField field) {
        field.setFont(new Font("Arial", Font.PLAIN, 13));
        field.setBorder(BorderFactory.createLineBorder(YELLOW, 2));
    }

    /**
     * Handles the book action.
     */
    private void handleBook() {
        try {
            String userName = username;
            String email = emailField.getText().trim();
            String date = dateField.getText().trim();
            String time = timeField.getText().trim();
            int duration = Integer.parseInt(durationField.getText().trim());
            String type = (String) typeCombo.getSelectedItem();
            String roomName = (String) roomCombo.getSelectedItem();

            if (date.isEmpty() || time.isEmpty() || email.isEmpty()) {
                showError("❌ Please fill all fields.");
                return;
            }

            List<Equipment> equipment = new ArrayList<>();
            if ("Virtual Room".equals(roomName)) {
                equipment.add(new Equipment("E1", "Video Conference System", true));
            }

            MeetingRoom room = new MeetingRoom("R1", roomName, 10, "Medium", equipment);
            User user = new User(username, userName, email, "0599000000");
            Reservation reservation = createReservation(type, date, time, duration, room);

            reservation.setUserName(userName);
            reservation.setUserEmail(email);

            reservationService.book(reservation, user);

            if (cateringCheckBox.isSelected()) {
                String catering = (String) cateringCombo.getSelectedItem();
                showSuccess("🎉 Reservation confirmed! Catering: " + catering);
            } else {
                showSuccess("🎉 Reservation confirmed successfully!");
            }

            clearFields();
        } catch (NumberFormatException exception) {
            showError("❌ Duration must be a number (30, 60, or 120).");
        } catch (IllegalArgumentException exception) {
            showError("❌ " + exception.getMessage());
        }
    }

    /**
     * Creates a reservation based on the selected type.
     *
     * @param type reservation type
     * @param date reservation date
     * @param time reservation time
     * @param duration reservation duration
     * @param room selected room
     * @return created reservation
     */
    private Reservation createReservation(String type, String date,
                                          String time, int duration,
                                          MeetingRoom room) {
        String id = "RES-" + (int) (Math.random() * 1000);

        switch (type) {
            case "Group":
                return new GroupReservation(id, date, time, duration, room, 2);
            case "Virtual":
                return new VirtualReservation(id, date, time, duration, room);
            case "InPerson":
                return new InPersonReservation(id, date, time, duration, room);
            case "Urgent":
                return new UrgentReservation(id, date, time, duration, room);
            case "FollowUp":
                return new FollowUpReservation(id, date, time, duration, room);
            case "Assessment":
                return new AssessmentReservation(id, date, time, duration, room);
            default:
                return new IndividualReservation(id, date, time, duration, room);
        }
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        emailField.setText("");
        dateField.setText("");
        timeField.setText("");
        durationField.setText("");
        statusLabel.setText(" ");
        cateringCheckBox.setSelected(false);
        cateringCombo.setEnabled(false);
    }

    /**
     * Shows a success message.
     *
     * @param message success message
     */
    private void showSuccess(String message) {
        statusLabel.setForeground(SUCCESS_GREEN);
        statusLabel.setText(message);
    }

    /**
     * Shows an error message.
     *
     * @param message error message
     */
    private void showError(String message) {
        statusLabel.setForeground(ERROR_PINK);
        statusLabel.setText(message);
    }
}
