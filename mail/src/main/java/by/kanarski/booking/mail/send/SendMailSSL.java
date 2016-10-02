package by.kanarski.booking.mail.send;

import by.kanarski.booking.constants.AuthenticationKeys;
import by.kanarski.booking.constants.MailMessagesKeys;
import by.kanarski.booking.mail.authenticatiors.BookingAuthenticator;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.utils.properties.PropertiesManager;

import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {

    private static SendMailSSL instance;

    private ResourceBundle bookingAccount = ResourceBuilder.AUTHENTIFICATION.create();

    private SendMailSSL() {

    }

    public static synchronized SendMailSSL getInstance() {
        if (instance == null) {
            instance = new SendMailSSL();
        }
        return instance;
    }

    private void send(String to, String subject, String messageText) {
        Session session = defineSession();
        try {
            String from = bookingAccount.getString(AuthenticationKeys.USER_NAME);
            InternetAddress fromAdress = new InternetAddress(from);
            InternetAddress[] toAdress = InternetAddress.parse(to);
            Message message = new MimeMessage(session);
            message.setFrom(fromAdress);
            message.setRecipients(Message.RecipientType.TO, toAdress);
            message.setSubject(subject);
            message.setText(messageText);
            Transport.send(message);
        } catch (MessagingException e) {
            // TODO: 01.10.2016 Обработать
            throw new RuntimeException(e);
        }
    }

    private Session defineSession() {
        Properties properties = PropertiesManager.getInstance().getOutgoingMailProperties();
        Authenticator authenticator = new BookingAuthenticator();
        Session session = Session.getDefaultInstance(properties, authenticator);
        return session;
    }

    public void sendPassword(String to, String password, Locale locale) {
        ResourceBundle mailMessages = ResourceBuilder.MAIL_MESSAGES.setLocale(locale).create();
        String subject = mailMessages.getString(MailMessagesKeys.SUBJECT_RECOVERY_PASSWORD);
        subject = "[booking.by] " + subject;
        String message = mailMessages.getString(MailMessagesKeys.MESSAGE_YOUR_PASSWORD);
        message += " " + password;
        send(to, subject, message);
    }

}