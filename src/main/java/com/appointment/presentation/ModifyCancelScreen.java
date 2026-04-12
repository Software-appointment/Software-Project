package com.appointment.presentation;

import com.appointment.domain.TimeSlot;
import com.appointment.service.ReservationService;

import javax.swing.*;
import java.awt.*;

/**
 * Screen for modifying or cancelling an existing reservation.
 *
 * @author Student C
 * @version 1.0
 */
public class ModifyCancelScreen extends JPanel {

    /** Colors */
    private static final Color BLUE      = new Color(66, 165, 245);
    private static final Color YELLOW    = new Color(253, 216, 53);
    private static final Color GREEN     = new Color(102, 187, 106);
    private static final Color PINK      = new Color(244, 143, 177);
    private static final Color BG        = new Color(248, 251, 255);
    private static final Color DARK_BLUE = new Color(13, 71, 161);

    private ReservationService reservationService;

    private JTextField idField;
    private JTextField dateField;
    private JTextField timeField;
    private JLabel statusLabel;

    /**
     * Constructor for ModifyCancelScreen.
     *
     * @param reservationService the service to modify or cancel reservations
     */
    public ModifyCancelScreen(ReservationService reservationService) {
        this.reservationService = reservationService;
        setBackground(BG);
        setLayout(new BorderLayout(10, 10));
        buildUI();
    }

    /**
     * Builds the UI components.
     */
    private void buildUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(PINK);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        JLabel title = new JLabel("✏️ Modify / Cancel Reservation");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(136, 14, 79));
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // Form
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BG);
        form.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Reservation ID
        gbc.gridx = 0; gbc.gridy = 0;
        JLabel idLabel = new JLabel("🔍 Reservation ID:");
        idLabel.setFont(new Font("Arial", Font.BOLD, 13));
        idLabel.setForeground(DARK_BLUE);
        form.add(idLabel, gbc);

        gbc.gridx = 1;
        idField = new JTextField(20);
        idField.setFont(new Font("Arial", Font.PLAIN, 13));
        idField.setBorder(BorderFactory.createLineBorder(YELLOW, 2));
        form.add(idField, gbc);

        // New Date
        gbc.gridx = 0; gbc.gridy = 1;
        JLabel dateLabel = new JLabel("📅 New Date:");
        dateLabel.setFont(new Font("Arial", Font.BOLD, 13));
        dateLabel.setForeground(DARK_BLUE);
        form.add(dateLabel, gbc);

        gbc.gridx = 1;
        dateField = new JTextField(20);
        dateField.setFont(new Font("Arial", Font.PLAIN, 13));
        dateField.setBorder(BorderFactory.createLineBorder(YELLOW, 2));
        form.add(dateField, gbc);

        // New Time
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel timeLabel = new JLabel("⏰ New Time:");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 13));
        timeLabel.setForeground(DARK_BLUE);
        form.add(timeLabel, gbc);

        gbc.gridx = 1;
        timeField = new JTextField(20);
        timeField.setFont(new Font("Arial", Font.PLAIN, 13));
        timeField.setBorder(BorderFactory.createLineBorder(YELLOW, 2));
        form.add(timeField, gbc);

        add(form, BorderLayout.CENTER);

        // Status label
        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(BG);

        JButton modifyBtn = new JButton("✅ Modify");
        modifyBtn.setBackground(GREEN);
        modifyBtn.setForeground(new Color(27, 94, 32));
        modifyBtn.setFont(new Font("Arial", Font.BOLD, 13));
        modifyBtn.setFocusPainted(false);
        modifyBtn.setBorderPainted(false);
        modifyBtn.addActionListener(e -> handleModify());

        JButton cancelBtn = new JButton("❌ Cancel Reservation");
        cancelBtn.setBackground(PINK);
        cancelBtn.setForeground(new Color(136, 14, 79));
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 13));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.addActionListener(e -> handleCancel());

        JButton clearBtn = new JButton("🔄 Clear");
        clearBtn.setBackground(BLUE);
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFont(new Font("Arial", Font.BOLD, 13));
        clearBtn.setFocusPainted(false);
        clearBtn.setBorderPainted(false);
        clearBtn.addActionListener(e -> clearFields());

        btnPanel.add(modifyBtn);
        btnPanel.add(cancelBtn);
        btnPanel.add(clearBtn);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(BG);
        southPanel.add(statusLabel, BorderLayout.NORTH);
        southPanel.add(btnPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Handles the modify action.
     */
    private void handleModify() {
        String id = idField.getText().trim();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();

        if (id.isEmpty()) {
            showError("❌ Please enter a Reservation ID.");
            return;
        }

        try {
            reservationService.modify(id,
                date.isEmpty() ? null : date,
                time.isEmpty() ? null : time,
                -1);
            showSuccess("✅ Reservation modified successfully!");
        } catch (IllegalArgumentException ex) {
            showError("❌ " + ex.getMessage());
        }
    }

    /**
     * Handles the cancel action.
     */
    private void handleCancel() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            showError("❌ Please enter a Reservation ID.");
            return;
        }

        try {
            TimeSlot slot = new TimeSlot("T1", "00:00", "00:00");
            reservationService.cancel(id, slot);
            showSuccess("✅ Reservation cancelled successfully!");
        } catch (IllegalArgumentException ex) {
            showError("❌ " + ex.getMessage());
        }
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        idField.setText("");
        dateField.setText("");
        timeField.setText("");
        statusLabel.setText(" ");
    }

    /**
     * Shows a success message.
     *
     * @param message the message to display
     */
    private void showSuccess(String message) {
        statusLabel.setForeground(new Color(27, 94, 32));
        statusLabel.setText(message);
    }

    /**
     * Shows an error message.
     *
     * @param message the message to display
     */
    private void showError(String message) {
        statusLabel.setForeground(new Color(136, 14, 79));
        statusLabel.setText(message);
    }
}