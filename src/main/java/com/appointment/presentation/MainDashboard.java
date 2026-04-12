package com.appointment.presentation;

import com.appointment.repository.ReservationRepository;
import com.appointment.repository.UserRepository;
import com.appointment.service.AdminReservationService;
import com.appointment.service.AuthService;
import com.appointment.service.NotificationService;
import com.appointment.service.ReservationService;
import com.appointment.service.RoomService;

import javax.swing.*;
import java.awt.*;

/**
 * Main dashboard screen shown after successful login.
 *
 * @author Student A
 * @version 1.0
 */
public class MainDashboard extends JPanel {

    private static final Color BLUE      = new Color(66, 165, 245);
    private static final Color YELLOW    = new Color(253, 216, 53);
    private static final Color PINK      = new Color(244, 143, 177);
    private static final Color BG        = new Color(248, 251, 255);
    private static final Color DARK_BLUE = new Color(13, 71, 161);

    private String username;
    private boolean isAdmin;
    private ReservationService reservationService;
    private RoomService roomService;
    private AdminReservationService adminService;
    private AuthService authService;
    private MainApp mainApp;
    private JPanel contentPanel;

    public MainDashboard(String username, boolean isAdmin, MainApp mainApp) {
        this.username = username;
        this.isAdmin  = isAdmin;
        this.mainApp  = mainApp;

        UserRepository userRepo          = mainApp.getUserRepository();
        ReservationRepository resRepo = mainApp.getReservationRepository();
        NotificationService notifService = new NotificationService();

        this.reservationService = new ReservationService(resRepo, notifService);
        this.roomService        = new RoomService(mainApp.getRoomRepository());
        this.authService        = new AuthService(userRepo);
        this.adminService       = new AdminReservationService(resRepo, userRepo);

        setLayout(new BorderLayout());
        setBackground(BG);
        buildUI();
    }

    private void buildUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLUE);
        header.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        JLabel title = new JLabel("Meeting Room Reservation System");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        String roleText = isAdmin ? "Admin" : "User";
        JLabel welcome = new JLabel("Welcome, " + username + "  |  " + roleText);
        welcome.setFont(new Font("Arial", Font.PLAIN, 15));
        welcome.setForeground(new Color(227, 242, 253));

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(BLUE);
        titlePanel.add(title);
        titlePanel.add(welcome);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(PINK);
        logoutBtn.setForeground(new Color(136, 14, 79));
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 14));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setPreferredSize(new Dimension(100, 40));
        logoutBtn.addActionListener(e -> mainApp.showLogin());

        header.add(titlePanel, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        // Navigation
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        navPanel.setBackground(new Color(230, 240, 255));
        navPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        String[] tabs;
        if (isAdmin) {
            tabs = new String[]{"Rooms", "My Reservations", "Admin Panel"};
        } else {
            tabs = new String[]{"Rooms", "Book", "My Reservations", "Modify/Cancel"};
        }

        JButton[] navBtns = new JButton[tabs.length];
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(BG);

        for (int i = 0; i < tabs.length; i++) {
            final int idx = i;
            navBtns[i] = new JButton(tabs[i]);
            navBtns[i].setFont(new Font("Arial", Font.BOLD, 14));
            navBtns[i].setFocusPainted(false);
            navBtns[i].setBorderPainted(true);
            navBtns[i].setBackground(Color.WHITE);
            navBtns[i].setForeground(DARK_BLUE);
            navBtns[i].setBorder(BorderFactory.createLineBorder(BLUE, 1));
            navBtns[i].setPreferredSize(new Dimension(160, 40));
            navBtns[i].addActionListener(e -> {
                for (JButton b : navBtns) {
                    b.setBackground(Color.WHITE);
                    b.setForeground(DARK_BLUE);
                }
                navBtns[idx].setBackground(YELLOW);
                navBtns[idx].setForeground(DARK_BLUE);
                showTab(idx);
            });
            navPanel.add(navBtns[i]);
        }
        navBtns[0].setBackground(YELLOW);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(header, BorderLayout.NORTH);
        topPanel.add(navPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        showTab(0);
    }

    private void showTab(int idx) {
        contentPanel.removeAll();

        if (isAdmin) {
            switch (idx) {
                case 0: contentPanel.add(new ViewRoomsScreen(roomService), BorderLayout.CENTER); break;
                case 1: contentPanel.add(new MyReservationsScreen(reservationService), BorderLayout.CENTER); break;
                case 2: contentPanel.add(new AdminPanelScreen(adminService, authService, username), BorderLayout.CENTER); break;
            }
        } else {
            switch (idx) {
                case 0: contentPanel.add(new ViewRoomsScreen(roomService), BorderLayout.CENTER); break;
                case 1: contentPanel.add(new BookRoomScreen(reservationService, roomService, username), BorderLayout.CENTER); break;
                case 2: contentPanel.add(new MyReservationsScreen(reservationService), BorderLayout.CENTER); break;
                case 3: contentPanel.add(new ModifyCancelScreen(reservationService), BorderLayout.CENTER); break;
            }
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }
}