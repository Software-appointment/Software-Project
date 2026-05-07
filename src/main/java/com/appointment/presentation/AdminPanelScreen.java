package com.appointment.presentation;

import com.appointment.domain.Reservation;
import com.appointment.service.AdminReservationService;
import com.appointment.service.AuthService;
import com.appointment.service.ReservationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Screen for administrator-only reservation management.
 * Allows admin to modify or cancel any reservation.
 *
 * @author Student C
 * @version 1.0
 */
public class AdminPanelScreen extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color BLUE      = new Color(66, 165, 245);
    private static final Color YELLOW    = new Color(253, 216, 53);
    private static final Color GREEN     = new Color(102, 187, 106);
    private static final Color PINK      = new Color(244, 143, 177);
    private static final Color BG        = new Color(248, 251, 255);
    private static final Color DARK_BLUE = new Color(13, 71, 161);

    private transient AdminReservationService adminService;
    private transient ReservationService reservationService;
    private transient AuthService authService;

    private String adminUsername;
    private JTextField idField;
    private JTextField userEmailField;
    private JTextField userNameField;
    private JTextField dateField;
    private JTextField timeField;
    private JLabel statusLabel;
    private DefaultTableModel tableModel;

    /**
     * Constructor for AdminPanelScreen.
     *
     * @param adminService the admin reservation service
     * @param authService the authentication service
     * @param adminUsername the logged-in admin username
     */
    public AdminPanelScreen(AdminReservationService adminService,
                            AuthService authService,
                            String adminUsername) {
        this.adminService = adminService;
        this.authService = authService;
        this.adminUsername = adminUsername;

        setBackground(BG);
        setLayout(new BorderLayout(10, 10));
        buildUI();
    }

    /**
     * Constructor with ReservationService to load reservations table.
     *
     * @param adminService the admin reservation service
     * @param authService the authentication service
     * @param adminUsername the logged-in admin username
     * @param reservationService the reservation service
     */
    public AdminPanelScreen(AdminReservationService adminService,
                            AuthService authService,
                            String adminUsername,
                            ReservationService reservationService) {
        this.adminService = adminService;
        this.authService = authService;
        this.adminUsername = adminUsername;
        this.reservationService = reservationService;

        setBackground(BG);
        setLayout(new BorderLayout(10, 10));
        buildUI();
    }

    /**
     * Builds the UI components.
     */
    private void buildUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(YELLOW);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));

        JLabel title = new JLabel("⚙️ Admin Panel");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(DARK_BLUE);

        JLabel adminLabel = new JLabel("👤 " + adminUsername);
        adminLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        adminLabel.setForeground(DARK_BLUE);

        header.add(title, BorderLayout.WEST);
        header.add(adminLabel, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        String[] columns = {
            "Reservation ID",
            "User Name",
            "User Email",
            "Date",
            "Time",
            "Type",
            "Status"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(BLUE);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setSelectionBackground(new Color(227, 242, 253));
        table.setSelectionForeground(DARK_BLUE);

        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                int row = table.getSelectedRow();
                idField.setText((String) tableModel.getValueAt(row, 0));
                userNameField.setText((String) tableModel.getValueAt(row, 1));
                userEmailField.setText((String) tableModel.getValueAt(row, 2));
            }
        });

        loadTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 140));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(BG);
        form.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel infoLabel = new JLabel("🔐 Select a reservation from the table or enter ID manually");
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoLabel.setForeground(BLUE);
        form.add(infoLabel, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        addLabel(form, gbc, "🔍 Reservation ID:");

        gbc.gridx = 1;
        idField = new JTextField(20);
        styleField(idField);
        form.add(idField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        addLabel(form, gbc, "👤 User Name:");

        gbc.gridx = 1;
        userNameField = new JTextField(20);
        styleField(userNameField);
        form.add(userNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        addLabel(form, gbc, "📧 User Email:");

        gbc.gridx = 1;
        userEmailField = new JTextField(20);
        styleField(userEmailField);
        form.add(userEmailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        addLabel(form, gbc, "📅 New Date (yyyy-MM-dd):");

        gbc.gridx = 1;
        dateField = new JTextField(20);
        styleField(dateField);
        form.add(dateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        addLabel(form, gbc, "⏰ New Time (HH:mm):");

        gbc.gridx = 1;
        timeField = new JTextField(20);
        styleField(timeField);
        form.add(timeField, gbc);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(BG);
        centerPanel.add(scrollPane, BorderLayout.NORTH);
        centerPanel.add(form, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(BG);

        JButton modifyBtn = new JButton("✅ Modify");
        modifyBtn.setBackground(GREEN);
        modifyBtn.setForeground(new Color(27, 94, 32));
        modifyBtn.setFont(new Font("Arial", Font.BOLD, 13));
        modifyBtn.setFocusPainted(false);
        modifyBtn.setBorderPainted(false);
        modifyBtn.addActionListener(event -> handleModify());

        JButton cancelBtn = new JButton("❌ Cancel Reservation");
        cancelBtn.setBackground(PINK);
        cancelBtn.setForeground(new Color(136, 14, 79));
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 13));
        cancelBtn.setFocusPainted(false);
        cancelBtn.setBorderPainted(false);
        cancelBtn.addActionListener(event -> handleCancel());

        JButton refreshBtn = new JButton("🔄 Refresh");
        refreshBtn.setBackground(BLUE);
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 13));
        refreshBtn.setFocusPainted(false);
        refreshBtn.setBorderPainted(false);
        refreshBtn.addActionListener(event -> {
            loadTable();
            clearFields();
        });

        btnPanel.add(modifyBtn);
        btnPanel.add(cancelBtn);
        btnPanel.add(refreshBtn);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.setBackground(BG);
        southPanel.add(statusLabel, BorderLayout.NORTH);
        southPanel.add(btnPanel, BorderLayout.CENTER);

        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Loads all reservations into the table.
     */
    private void loadTable() {
        tableModel.setRowCount(0);

        if (reservationService == null) {
            return;
        }

        List<Reservation> list = reservationService.getAll();

        for (Reservation reservation : list) {
            tableModel.addRow(new Object[] {
                reservation.getReservationId(),
                reservation.getUserName() != null ? reservation.getUserName() : "-",
                reservation.getUserEmail() != null ? reservation.getUserEmail() : "-",
                reservation.getDate(),
                reservation.getTime(),
                reservation.getType(),
                reservation.getStatus()
            });
        }
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
        field.setBorder(BorderFactory.createLineBorder(BLUE, 2));
    }

    /**
     * Handles the admin modify action.
     */
    private void handleModify() {
        String id = idField.getText().trim();
        String userName = userNameField.getText().trim();
        String userEmail = userEmailField.getText().trim();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();

        if (id.isEmpty() || userEmail.isEmpty() || userName.isEmpty()) {
            showError("❌ Please select a reservation or enter ID, Name, and Email.");
            return;
        }

        try {
            adminService.modifyReservation(
                    id,
                    adminUsername,
                    date.isEmpty() ? null : date,
                    time.isEmpty() ? null : time,
                    userEmail,
                    userName
            );

            showSuccess("✅ Reservation modified successfully!");
            loadTable();
            clearFields();
        } catch (Exception exception) {
            showError("❌ " + exception.getMessage());
        }
    }

    /**
     * Handles the admin cancel action.
     */
    private void handleCancel() {
        String id = idField.getText().trim();
        String userName = userNameField.getText().trim();
        String userEmail = userEmailField.getText().trim();

        if (id.isEmpty() || userEmail.isEmpty() || userName.isEmpty()) {
            showError("❌ Please select a reservation or enter ID, Name, and Email.");
            return;
        }

        try {
            adminService.cancelReservation(id, adminUsername, userEmail, userName);
            showSuccess("✅ Reservation cancelled successfully!");
            loadTable();
            clearFields();
        } catch (Exception exception) {
            showError("❌ " + exception.getMessage());
        }
    }

    /**
     * Clears all input fields.
     */
    private void clearFields() {
        idField.setText("");
        userNameField.setText("");
        userEmailField.setText("");
        dateField.setText("");
        timeField.setText("");
        statusLabel.setText(" ");
    }

    /**
     * Shows a success message.
     *
     * @param message success message
     */
    private void showSuccess(String message) {
        statusLabel.setForeground(new Color(27, 94, 32));
        statusLabel.setText(message);
    }

    /**
     * Shows an error message.
     *
     * @param message error message
     */
    private void showError(String message) {
        statusLabel.setForeground(new Color(136, 14, 79));
        statusLabel.setText(message);
    }
}
