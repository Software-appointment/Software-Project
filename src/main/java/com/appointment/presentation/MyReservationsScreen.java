package com.appointment.presentation;

import com.appointment.domain.Reservation;
import com.appointment.service.ReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Screen for displaying all reservations for the current user.
 *
 * @author Student C
 * @version 1.0
 */
public class MyReservationsScreen extends JPanel {

    /** Colors */
    private static final Color BLUE       = new Color(66, 165, 245);
    private static final Color YELLOW     = new Color(253, 216, 53);
    private static final Color GREEN      = new Color(102, 187, 106);
    private static final Color PINK       = new Color(244, 143, 177);
    private static final Color BG         = new Color(248, 251, 255);
    private static final Color DARK_BLUE  = new Color(13, 71, 161);

    private ReservationService reservationService;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Constructor for MyReservationsScreen.
     *
     * @param reservationService the service to fetch reservations
     */
    public MyReservationsScreen(ReservationService reservationService) {
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
        header.setBackground(BLUE);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        JLabel title = new JLabel("📋 My Reservations");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.WHITE);
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Date", "Time", "Duration", "Room", "Type", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(tableModel);
        table.setBackground(Color.WHITE);
        table.setForeground(DARK_BLUE);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.setRowHeight(30);
        table.getTableHeader().setBackground(YELLOW);
        table.getTableHeader().setForeground(DARK_BLUE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scroll, BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(BG);

        JButton refreshBtn = new JButton("🔄 Refresh");
        refreshBtn.setBackground(BLUE);
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 13));
        refreshBtn.setFocusPainted(false);
        refreshBtn.setBorderPainted(false);
        refreshBtn.addActionListener(e -> loadReservations());

        JButton modifyBtn = new JButton("✏️ Modify");
        modifyBtn.setBackground(YELLOW);
        modifyBtn.setForeground(DARK_BLUE);
        modifyBtn.setFont(new Font("Arial", Font.BOLD, 13));
        modifyBtn.setFocusPainted(false);
        modifyBtn.setBorderPainted(false);
        modifyBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a reservation first.");
                return;
            }
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            String newDate = JOptionPane.showInputDialog(this, "Enter new date (yyyy-MM-dd):");
            if (newDate == null) return;
            String newTime = JOptionPane.showInputDialog(this, "Enter new time (HH:mm):");
            if (newTime == null) return;
            try {
                reservationService.modify(id, newDate, newTime, -1);
                JOptionPane.showMessageDialog(this, "✅ Reservation modified successfully!");
                loadReservations();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage());
            }
        });
        JButton cancelBtn = new JButton("❌ Cancel");
        cancelBtn.setBackground(PINK);
        cancelBtn.setForeground(new Color(136, 14, 79));
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 13));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a reservation first.");
                return;
            }
            String id = (String) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel reservation " + id + "?",
                "Confirm Cancel", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    reservationService.cancel(id, new com.appointment.domain.TimeSlot("T1", "00:00", "00:00"));
                    JOptionPane.showMessageDialog(this, "✅ Reservation cancelled successfully!");
                    loadReservations();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(this, "❌ " + ex.getMessage());
                }
            }
        });

        btnPanel.add(refreshBtn);
        btnPanel.add(modifyBtn);
        btnPanel.add(cancelBtn);
        add(btnPanel, BorderLayout.SOUTH);

        loadReservations();
    }

    /**
     * Loads all reservations into the table.
     */
    public void loadReservations() {
        tableModel.setRowCount(0);
        List<Reservation> reservations = reservationService.getAll();
        for (Reservation r : reservations) {
            tableModel.addRow(new Object[]{
                r.getReservationId(),
                r.getDate(),
                r.getTime(),
                r.getDuration() + " min",
                r.getRoom().getRoomName(),
                r.getType(),
                r.getStatus()
            });
        }
    }
}