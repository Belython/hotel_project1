package by.kanarski.booking.mail.authenticatiors;

import by.kanarski.booking.constants.AuthenticationKeys;
import by.kanarski.booking.managers.ResourceBuilder;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.ResourceBundle;

public class BookingAuthenticator extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        ResourceBundle bookingAccount = ResourceBuilder.AUTHENTIFICATION.create();
        String userName = bookingAccount.getString(AuthenticationKeys.USER_NAME);
        String password = bookingAccount.getString(AuthenticationKeys.PASSWORD);
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(userName, password);
        return passwordAuthentication;
    }
}
