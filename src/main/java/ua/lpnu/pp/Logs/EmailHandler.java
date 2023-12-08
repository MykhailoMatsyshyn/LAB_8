package ua.lpnu.pp.Logs;

import java.util.Properties;
import java.util.logging.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailHandler extends Handler {
    private String username;
    private String password;
    private String recipient;
    private String subject;

    public EmailHandler(String username, String password, String recipient, String subject) {
        this.username = username;
        this.password = password;
        this.recipient = recipient;
        this.subject = subject;
    }

    @Override
    public void publish(LogRecord record) {
        if (record.getLevel().equals(Level.SEVERE)) {
            try {
                sendEmail(record);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendEmail(LogRecord record) throws MessagingException {
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        message.setSubject(subject);
        message.setText(record.getMessage());

        Transport.send(message);
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}
}

