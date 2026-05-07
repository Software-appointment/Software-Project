package com.appointment.presentation;

import com.appointment.domain.Reservation;
import com.appointment.domain.TimeSlot;
import com.appointment.service.ReservationService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

/**
 * Screen for displaying reservations for the current user.
 *
 * @author Student C
 * @version 1.0
 */
public class MyReservationsScreen extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color BLUE = new Color(66, 165, 245);
    private static final Color YELLOW = new Color(253, 216, 53);
    private static final Color PINK = new Color(244, 143, 177);
    private static final Color BG = new Color(248, 251, 255);
    private static final Color DARK_BLUE = new Color(13, 71, 161);
    private static final Color ERROR_PINK = new Color(136, 14, 79);

    private transient ReservationService reservationService;

    private String currentUser;
    private boolean isAdmin;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Constructor for MyReservationsScreen.
     *
     * @param reservationService the service to fetch reservations
     * @param currentUser the logged-in username
     * @param isAdmin whether the current user is an admin
     */
    public MyReservationsScreen(ReservationService reservationService,
                                String currentUser,
                                boolean isAdmin) {
        this.reservationService = reservationService;
        this.currentUser = currentUser;
        this.isAdmin = isAdmin;

        setBackground(BG);
        setLayout(new BorderLayout(10, 10));
        buildUI();
    }

    private void buildUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLUE);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("📋 " + (isAdmin ? "All Reservations" : "My Reservations"));
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.WHITE);

        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        String[] columns = {
            "ID",
            "Date",
            "Time",
            "Duration",
            "Room",
            "Type",
            "Status",
            "User"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
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

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(BG);

        JButton refreshBtn = new JButton("🔄 Refresh");
        refreshBtn.setBackground(BLUE);
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 13));
        refreshBtn.setFocusPainted(false);
        refreshBtn.setBorderPainted(false);
        refreshBtn.addActionListener(event -> loadReservations());

        JButton modifyBtn = new JButton("✏️ Modify");
        modifyBtn.setBackground(YELLOW);
        modifyBtn.setForeground(DARK_BLUE);
        modifyBtn.setFont(new Font("Arial", Font.BOLD, 13));
        modifyBtn.setFocusPainted(false);
        modifyBtn.setBorderPainted(false);
        modifyBtn.addActionListener(event -> handleModify());

        JButton cancelBtn = new JButton("❌ Cancel");
        cancelBtn.setBackground(PINK);
        cancelBtn.setForeground(ERROR_PINK);
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 13));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.addActionListener(event -> handleCancel());

        btnPanel.add(refreshBtn);
        btnPanel.add(modifyBtn);
        btnPanel.add(cancelBtn);

        add(btnPanel, BorderLayout.SOUTH);

        loadReservations();
    }

    private void handleModify() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a reservation first.");
            return;
        }

        String id = (String) tableModel.getValueAt(selectedRow, 0);
        String newDate = JOptionPane.showInputDialog(this, "Enter new date (yyyy-MM-dd):");

        if (newDate == null) {
            return;
        }

        String newTime = JOptionPane.showInputDialog(this, "Enter new time (HH:mm):");

        if (newTime == null) {
            return;
        }

        try {
            reservationService.modify(id, newDate, newTime, -1);
            JOptionPane.showMessageDialog(this, "✅ Reservation modified successfully!");
            loadReservations();
        } catch (IllegalArgumentException exception) {
            JOptionPane.showMessageDialog(this, "❌ " + exception.getMessage());
        }
    }

    private void handleCancel() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a reservation first.");
            return;
        }

        String id = (String) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to cancel reservation " + id + "?",
                "Confirm Cancel",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                reservationService.cancel(id, new TimeSlot("T1", "00:00", "00:00"));
                JOptionPane.showMessageDialog(this, "✅ Reservation cancelled successfully!");
                loadReservations();
            } catch (IllegalArgumentException exception) {
                JOptionPane.showMessageDialog(this, "❌ " + exception.getMessage());
            }
        }
    }

    /**
     * Loads reservations into the table.
     * Admin sees all, user sees only their own.
     */
    public void loadReservations() {
        tableModel.setRowCount(0);

        List<Reservation> reservations = reservationService.getAll();

        for (Reservation reservation : reservations) {
            if (isAdmin || currentUser.equalsIgnoreCase(reservation.getUserName())) {
                tableModel.addRow(new Object[] {
                    reservation.getReservationId(),
                    reservation.getDate(),
                    reservation.getTime(),
                    reservation.getDuration() + " min",
                    reservation.getRoom().getRoomName(),
                    reservation.getType(),
                    reservation.getStatus(),
                    reservation.getUserName()
                });
            }
        }
    }
}
