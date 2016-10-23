package by.kanarski.booking.commands;

import by.kanarski.booking.constants.OperationMessageKeys;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.utils.BookingSystemLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class AbstractCommand implements ICommand {

    protected void handleServiceException(HttpServletRequest request, Exception exception) {

        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
        String errorMessage = bundle.getString(OperationMessageKeys.ERROR_DATABASE);
        request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
        BookingSystemLogger.getInstance().logError(getClass(), errorMessage, exception);
    }

    protected void handleServiceException(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
        String errorMessage = bundle.getString(OperationMessageKeys.ERROR_DATABASE);
        request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
    }

}
