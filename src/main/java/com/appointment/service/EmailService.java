package com.appointment.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import io.github.cdimascio.dotenv.Dotenv;
import java.util.Properties;

/**
 * Service responsible for sending real emails via Gmail SMTP.
 *
 * @author Student A
 * @version 1.0
 */
public class EmailService {

    private final String username;
    private final String password;

    /**
     * Loads credentials from .env file automatically.
     */
    public EmailService() {
        Dotenv dotenv = Dotenv.load();
        this.username = dotenv.get("EMAIL_USERNAME");
        this.password = dotenv.get("EMAIL_PASSWORD");
    }

    /**
     * Sends an email to the given address.
     *
     * @param to      recipient email address
     * @param subject email subject
     * @param body    email body text
     */
    public void sendEmail(String to, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(username, "Meeting Room System"));
            } catch (java.io.UnsupportedEncodingException e) {
                message.setFrom(new InternetAddress(username));
            }
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Email sent to " + to);
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}