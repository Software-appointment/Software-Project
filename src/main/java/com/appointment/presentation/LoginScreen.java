package com.appointment.presentation;

import com.appointment.service.AuthService;
import javax.swing.*;
import java.awt.*;

/**
 * Login screen for administrator and user authentication.
 *
 * @author Student A
 * @version 1.0
 */
public class LoginScreen extends JPanel {

    private static final Color BLUE      = new Color(66, 165, 245);
    private static final Color YELLOW    = new Color(253, 216, 53);
    private static final Color GREEN     = new Color(102, 187, 106);
    private static final Color PINK      = new Color(244, 143, 177);
    private static final Color BG        = new Color(248, 251, 255);
    private static final Color DARK_BLUE = new Color(13, 71, 161);

    private AuthService authService;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JLabel statusLabel;
    private JPanel adminPanel;
    private JPanel userPanel;
    private JRadioButton adminRadio;
    private JRadioButton userRadio;
    private MainApp mainApp;

    /**
     * Constructor for LoginScreen.
     *
     * @param authService the authentication service
     * @param mainApp the main application frame
     */
    public LoginScreen(AuthService authService, MainApp mainApp) {
        this.authService = authService;
        this.mainApp     = mainApp;
        setBackground(BG);
        setLayout(new BorderLayout());
        buildUI();
    }

    /**
     * Builds the UI components.
     */
    private void buildUI() {
        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BLUE);
        header.setBorder(BorderFactory.createEmptyBorder(20, 24, 20, 24));
        JLabel title = new JLabel("Meeting Room Reservation System");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        JLabel sub = new JLabel("Please select your role to login");
        sub.setFont(new Font("Arial", Font.PLAIN, 14));
        sub.setForeground(new Color(227, 242, 253));
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(BLUE);
        titlePanel.add(title);
        titlePanel.add(sub);
        header.add(titlePanel, BorderLayout.WEST);
        add(header, BorderLayout.NORTH);

        // Center
        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(BG);
        center.setBorder(BorderFactory.createEmptyBorder(20, 60, 10, 60));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 8, 10, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Role label
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        JLabel roleLabel = new JLabel("Login as:");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        roleLabel.setForeground(DARK_BLUE);
        roleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        center.add(roleLabel, gbc);

        // Radio buttons
        adminRadio = new JRadioButton("Administrator");
        adminRadio.setFont(new Font("Arial", Font.BOLD, 15));
        adminRadio.setForeground(new Color(136, 14, 79));
        adminRadio.setBackground(new Color(252, 228, 236));
        adminRadio.setSelected(true);
        adminRadio.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        userRadio = new JRadioButton("User");
        userRadio.setFont(new Font("Arial", Font.BOLD, 15));
        userRadio.setForeground(new Color(27, 94, 32));
        userRadio.setBackground(new Color(232, 245, 233));
        userRadio.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));

        ButtonGroup group = new ButtonGroup();
        group.add(adminRadio);
        group.add(userRadio);

        JPanel radioPanel = new JPanel(new GridLayout(1, 2, 16, 0));
        radioPanel.setBackground(BG);

        JPanel adminRadioBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
        adminRadioBox.setBackground(new Color(252, 228, 236));
        adminRadioBox.setBorder(BorderFactory.createLineBorder(PINK, 2));
        adminRadioBox.add(adminRadio);

        JPanel userRadioBox = new JPanel(new FlowLayout(FlowLayout.CENTER));
        userRadioBox.setBackground(new Color(232, 245, 233));
        userRadioBox.setBorder(BorderFactory.createLineBorder(GREEN, 2));
        userRadioBox.add(userRadio);

        radioPanel.add(adminRadioBox);
        radioPanel.add(userRadioBox);

        gbc.gridy = 1;
        center.add(radioPanel, gbc);

        // Admin panel
        adminPanel = new JPanel(new GridBagLayout());
        adminPanel.setBackground(new Color(255, 248, 249));
        adminPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(PINK, 2), "Administrator Login",
            0, 0, new Font("Arial", Font.BOLD, 13), new Color(136, 14, 79)));
        GridBagConstraints agbc = new GridBagConstraints();
        agbc.insets = new Insets(8, 12, 8, 12);
        agbc.fill = GridBagConstraints.HORIZONTAL;

        agbc.gridx = 0; agbc.gridy = 0;
        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(DARK_BLUE);
        adminPanel.add(userLabel, agbc);

        agbc.gridx = 1;
        usernameField = new JTextField(18);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameField.setPreferredSize(new Dimension(200, 36));
        usernameField.setBorder(BorderFactory.createLineBorder(YELLOW, 2));
        adminPanel.add(usernameField, agbc);

        agbc.gridx = 0; agbc.gridy = 1;
        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setForeground(DARK_BLUE);
        adminPanel.add(passLabel, agbc);

        agbc.gridx = 1;
        passwordField = new JPasswordField(18);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setPreferredSize(new Dimension(200, 36));
        passwordField.setBorder(BorderFactory.createLineBorder(YELLOW, 2));
        adminPanel.add(passwordField, agbc);

        // User panel
        userPanel = new JPanel(new GridBagLayout());
        userPanel.setBackground(new Color(241, 248, 241));
        userPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(GREEN, 2), "User Login",
            0, 0, new Font("Arial", Font.BOLD, 13), new Color(27, 94, 32)));
        GridBagConstraints ugbc = new GridBagConstraints();
        ugbc.insets = new Insets(8, 12, 8, 12);
        ugbc.fill = GridBagConstraints.HORIZONTAL;

        ugbc.gridx = 0; ugbc.gridy = 0;
        JLabel nameLabel = new JLabel("Your Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(DARK_BLUE);
        userPanel.add(nameLabel, ugbc);

        ugbc.gridx = 1;
        userNameField = new JTextField(18);
        userNameField.setFont(new Font("Arial", Font.PLAIN, 14));
        userNameField.setPreferredSize(new Dimension(200, 36));
        userNameField.setBorder(BorderFactory.createLineBorder(GREEN, 2));
        userPanel.add(userNameField, ugbc);

        gbc.gridy = 2; gbc.gridwidth = 2;
        center.add(adminPanel, gbc);

        gbc.gridy = 3;
        center.add(userPanel, gbc);
        userPanel.setVisible(false);

        add(center, BorderLayout.CENTER);

        adminRadio.addActionListener(e -> {
            adminPanel.setVisible(true);
            userPanel.setVisible(false);
        });
        userRadio.addActionListener(e -> {
            adminPanel.setVisible(false);
            userPanel.setVisible(true);
        });

        // Bottom
        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(BG);
        bottom.setBorder(BorderFactory.createEmptyBorder(0, 60, 24, 60));

        statusLabel = new JLabel(" ");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(new Color(136, 14, 79));

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(YELLOW);
        loginBtn.setForeground(DARK_BLUE);
        loginBtn.setFont(new Font("Arial", Font.BOLD, 16));
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.setPreferredSize(new Dimension(0, 45));
        loginBtn.addActionListener(e -> handleLogin());

        bottom.add(statusLabel, BorderLayout.NORTH);
        bottom.add(loginBtn, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    /**
     * Handles the login action.
     */
    private void handleLogin() {
        if (adminRadio.isSelected()) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (username.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Please enter username and password.");
                return;
            }
            if (authService.login(username, password)) {
                mainApp.showDashboard(username, true);
            } else {
                statusLabel.setText("Invalid credentials. Please try again.");
                passwordField.setText("");
            }
        } else {
            String name = userNameField.getText().trim();
            if (name.isEmpty()) {
                statusLabel.setText("Please enter your name.");
                return;
            }
            mainApp.showDashboard(name, false);
        }
    }
}