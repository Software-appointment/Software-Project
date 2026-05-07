package com.appointment.presentation;

import com.appointment.repository.ReservationRepository;
import com.appointment.repository.UserRepository;
import com.appointment.service.AdminReservationService;
import com.appointment.service.AuthService;
import com.appointment.service.NotificationService;
import com.appointment.service.ReservationService;
import com.appointment.service.RoomService;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

/**
 * Main dashboard screen shown after successful login.
 *
 * @author Student A
 * @version 1.0
 */
public class MainDashboard extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color BLUE = new Color(66, 165, 245);
    private static final Color YELLOW = new Color(253, 216, 53);
    private static final Color PINK = new Color(244, 143, 177);
    private static final Color BG = new Color(248, 251, 255);
    private static final Color DARK_BLUE = new Color(13, 71, 161);
    private static final Color LIGHT_BLUE = new Color(227, 242, 253);
    private static final Color NAV_BG = new Color(230, 240, 255);
    private static final Color LOGOUT_TEXT = new Color(136, 14, 79);

    private String username;
    private boolean isAdmin;

    private transient ReservationService reservationService;
    private transient RoomService roomService;
    private transient AdminReservationService adminService;
    private transient AuthService authService;
    private transient MainApp mainApp;

    private JPanel contentPanel;

    public MainDashboard(String username, boolean isAdmin, MainApp mainApp) {
        this.username = username;
        this.isAdmin = isAdmin;
        this.mainApp = mainApp;

        UserRepository userRepo = mainApp.getUserRepository();
        ReservationRepository resRepo = mainApp.getReservationRepository();

        NotificationService notifService = new NotificationService();
        notifService.addObserver(new com.appointment.observer.EmailNotificationObserver());

        this.reservationService = new ReservationService(resRepo, notifService);
        this.roomService = new RoomService(mainApp.getRoomRepository());
        this.authService = new AuthService(userRepo);
        this.adminService = new AdminReservationService(resRepo, userRepo);

        setLayout(new BorderLayout());
        setBackground(BG);
        buildUI();
    }

    private void buildUI() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLUE);
        header.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        JLabel title = new JLabel("Meeting Room Reservation System");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);

        String roleText = isAdmin ? "Admin" : "User";
        JLabel welcome = new JLabel("Welcome, " + username + "  |  " + roleText);
        welcome.setFont(new Font("Arial", Font.PLAIN, 15));
        welcome.setForeground(LIGHT_BLUE);

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(BLUE);
        titlePanel.add(title);
        titlePanel.add(welcome);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBackground(PINK);
        logoutBtn.setForeground(LOGOUT_TEXT);
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 14));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorderPainted(false);
        logoutBtn.setPreferredSize(new Dimension(100, 40));
        logoutBtn.addActionListener(event -> mainApp.showLogin());

        header.add(titlePanel, BorderLayout.WEST);
        header.add(logoutBtn, BorderLayout.EAST);

        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        navPanel.setBackground(NAV_BG);
        navPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        String[] tabs;

        if (isAdmin) {
            tabs = new String[] {"Rooms", "My Reservations", "Admin Panel"};
        } else {
            tabs = new String[] {"Rooms", "Book", "My Reservations", "Modify/Cancel"};
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

            navBtns[i].addActionListener(event -> {
                for (JButton button : navBtns) {
                    button.setBackground(Color.WHITE);
                    button.setForeground(DARK_BLUE);
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
            showAdminTab(idx);
        } else {
            showUserTab(idx);
        }

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showAdminTab(int idx) {
        switch (idx) {
            case 0:
                contentPanel.add(new ViewRoomsScreen(roomService), BorderLayout.CENTER);
                break;
            case 1:
                contentPanel.add(new MyReservationsScreen(reservationService, username, true),
                        BorderLayout.CENTER);
                break;
            case 2:
                contentPanel.add(new AdminPanelScreen(adminService, authService, username,
                        reservationService), BorderLayout.CENTER);
                break;
            default:
                contentPanel.add(new ViewRoomsScreen(roomService), BorderLayout.CENTER);
                break;
        }
    }

    private void showUserTab(int idx) {
        switch (idx) {
            case 0:
                contentPanel.add(new ViewRoomsScreen(roomService), BorderLayout.CENTER);
                break;
            case 1:
                contentPanel.add(new BookRoomScreen(reservationService, roomService, username),
                        BorderLayout.CENTER);
                break;
            case 2:
                contentPanel.add(new MyReservationsScreen(reservationService, username, false),
                        BorderLayout.CENTER);
                break;
            case 3:
                contentPanel.add(new ModifyCancelScreen(reservationService), BorderLayout.CENTER);
                break;
            default:
                contentPanel.add(new ViewRoomsScreen(roomService), BorderLayout.CENTER);
                break;
        }
    }
}
