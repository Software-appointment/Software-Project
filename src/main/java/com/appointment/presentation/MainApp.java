package com.appointment.presentation;

import com.appointment.domain.Administrator;
import com.appointment.domain.Equipment;
import com.appointment.domain.MeetingRoom;
import com.appointment.observer.EmailNotificationObserver;
import com.appointment.repository.ReservationRepository;
import com.appointment.repository.RoomRepository;
import com.appointment.repository.UserRepository;
import com.appointment.service.AuthService;
import com.appointment.service.NotificationService;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Main application entry point.
 * Manages screen navigation between Login and Dashboard.
 *
 * @author Student A
 * @version 1.0
 */
public class MainApp extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Color BG = new Color(248, 251, 255);

    private transient AuthService authService;
    private transient UserRepository userRepository;
    private transient RoomRepository roomRepository;
    private transient ReservationRepository reservationRepository;

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public MainApp() {
        userRepository = new UserRepository();
        roomRepository = new RoomRepository();
        reservationRepository = new ReservationRepository();
        authService = new AuthService(userRepository);

        NotificationService notificationService = new NotificationService();
        notificationService.addObserver(new EmailNotificationObserver());

        addDefaultAdmin();
        addDefaultRooms();

        setTitle("Meeting Room Reservation System");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BG);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        LoginScreen loginScreen = new LoginScreen(authService, this);
        mainPanel.add(loginScreen, "LOGIN");

        add(mainPanel);
        cardLayout.show(mainPanel, "LOGIN");

        setVisible(true);
    }

    private void addDefaultAdmin() {
        userRepository.addAdministrator(
                new Administrator("A1", "admin", "admin123")
        );
    }

    private void addDefaultRooms() {
        Equipment projector = new Equipment("E1", "Projector", true);
        Equipment videoConf = new Equipment("E2", "Video Conference System", true);
        Equipment whiteboard = new Equipment("E3", "Whiteboard", true);

        List<Equipment> smallEq = new ArrayList<>();
        smallEq.add(projector);
        smallEq.add(whiteboard);

        List<Equipment> mediumEq = new ArrayList<>();
        mediumEq.add(projector);
        mediumEq.add(whiteboard);

        List<Equipment> virtualEq = new ArrayList<>();
        virtualEq.add(videoConf);

        List<Equipment> largeEq = new ArrayList<>();
        largeEq.add(projector);
        largeEq.add(whiteboard);
        largeEq.add(videoConf);

        roomRepository.save(new MeetingRoom("R1", "Small Room", 5, "Small", smallEq));
        roomRepository.save(new MeetingRoom("R2", "Medium Room", 10, "Medium", mediumEq));
        roomRepository.save(new MeetingRoom("R3", "Large Conference", 20, "Large", largeEq));
        roomRepository.save(new MeetingRoom("R4", "Virtual Room", 10, "Medium", virtualEq));
    }

    public RoomRepository getRoomRepository() {
        return roomRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    /**
     * Returns the reservation repository.
     *
     * @return the reservation repository
     */
    public ReservationRepository getReservationRepository() {
        return reservationRepository;
    }

    public void showDashboard(String username, boolean isAdmin) {
        MainDashboard dashboard = new MainDashboard(username, isAdmin, this);
        mainPanel.add(dashboard, "DASHBOARD");
        cardLayout.show(mainPanel, "DASHBOARD");
    }

    public void showLogin() {
        cardLayout.show(mainPanel, "LOGIN");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainApp::new);
    }
}
