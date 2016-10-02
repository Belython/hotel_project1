package by.kanarski.booking.commands;

import by.kanarski.booking.constants.MessageKeys;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.managers.ResourceBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class AbstractCommand implements ICommand {

//    protected void handleServiceException(HttpServletRequest request, Exception exception) {
//
//        ResourceBundle bundle = ResourceBuilder.MESSAGES.setLocale()
//        String errorMessage = MessageManager.getInstance().getProperty(MessageKeys.ERROR_DATABASE);
//        request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
//        BookingSystemLogger.getInstance().logError(getClass(), errorMessage, exception);
//    }

    protected void handleServiceException(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        ResourceBundle bundle = ResourceBuilder.MESSAGES.setLocale(locale).create();
        String errorMessage = bundle.getString(MessageKeys.ERROR_DATABASE);
        request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
    }

}
