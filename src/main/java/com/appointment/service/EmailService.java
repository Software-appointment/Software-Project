package com.appointment.service;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Service responsible for sending real emails via Gmail SMTP.
 *
 * @author Student A
 * @version 1.0
 */
public class EmailService {

    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_STARTTLS = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";

    private static final String SMTP_AUTH_ENABLED = "true";
    private static final String STARTTLS_ENABLED = "true";
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SENDER_NAME = "Meeting Room System";

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
     * @param to recipient email address
     * @param subject email subject
     * @param body email body text
     */
    public void sendEmail(String to, String subject, String body) {
        Properties props = createMailProperties();
        Session session = createSession(props);

        try {
            Message message = new MimeMessage(session);

            setSender(message);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);
            System.out.println("Email sent to " + to);
        } catch (MessagingException exception) {
            System.err.println("Failed to send email: " + exception.getMessage());
        }
    }

    /**
     * Creates SMTP properties for Gmail.
     *
     * @return configured mail properties
     */
    private Properties createMailProperties() {
        Properties props = new Properties();

        props.put(MAIL_SMTP_AUTH, SMTP_AUTH_ENABLED);
        props.put(MAIL_SMTP_STARTTLS, STARTTLS_ENABLED);
        props.put(MAIL_SMTP_HOST, SMTP_HOST);
        props.put(MAIL_SMTP_PORT, SMTP_PORT);

        return props;
    }

    /**
     * Creates an authenticated mail session.
     *
     * @param props mail properties
     * @return authenticated session
     */
    private Session createSession(Properties props) {
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    /**
     * Sets the sender email address.
     *
     * @param message email message
     * @throws MessagingException if sender cannot be set
     */
    private void setSender(Message message) throws MessagingException {
        try {
            message.setFrom(new InternetAddress(username, SENDER_NAME));
        } catch (UnsupportedEncodingException exception) {
            message.setFrom(new InternetAddress(username));
        }
    }
}
