package com.appointment.presentation;

import com.appointment.domain.MeetingRoom;
import com.appointment.service.RoomService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Screen for viewing all available meeting rooms.
 *
 * @author Student B
 * @version 1.0
 */
public class ViewRoomsScreen extends JPanel {

    private static final Color BLUE      = new Color(66, 165, 245);
    private static final Color YELLOW    = new Color(253, 216, 53);
    private static final Color GREEN     = new Color(102, 187, 106);
    private static final Color BG        = new Color(248, 251, 255);
    private static final Color DARK_BLUE = new Color(13, 71, 161);

    private RoomService roomService;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Constructor for ViewRoomsScreen.
     *
     * @param roomService the service to fetch rooms
     */
    public ViewRoomsScreen(RoomService roomService) {
        this.roomService = roomService;
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
        header.setBackground(GREEN);
        header.setBorder(BorderFactory.createEmptyBorder(14, 20, 14, 20));
        JLabel title = new JLabel("🏠 Available Meeting Rooms");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(new Color(27, 94, 32));
        header.add(title, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // Table
        String[] columns = {"Room ID", "Room Name", "Capacity", "Size", "Equipment"};
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

        // Refresh button
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(BG);

        JButton refreshBtn = new JButton("🔄 Refresh Rooms");
        refreshBtn.setBackground(BLUE);
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Arial", Font.BOLD, 13));
        refreshBtn.setFocusPainted(false);
        refreshBtn.setBorderPainted(false);
        refreshBtn.addActionListener(e -> loadRooms());
        btnPanel.add(refreshBtn);
        add(btnPanel, BorderLayout.SOUTH);

        loadRooms();
    }

    /**
     * Loads all rooms into the table.
     */
    public void loadRooms() {
        tableModel.setRowCount(0);
        List<MeetingRoom> rooms = roomService.getAllRooms();
        if (rooms.isEmpty()) {
            tableModel.addRow(new Object[]{"—", "No rooms available", "—", "—", "—"});
        } else {
            for (MeetingRoom r : rooms) {
                String equipment = r.getAvailableEquipment().isEmpty() ? "None" :
                    r.getAvailableEquipment().stream()
                        .map(e -> e.getEquipmentName())
                        .reduce((a, b) -> a + ", " + b)
                        .orElse("None");
                tableModel.addRow(new Object[]{
                    r.getRoomId(),
                    r.getRoomName(),
                    r.getCapacity(),
                    r.getSize(),
                    equipment
                });
            }
        }
    }
}