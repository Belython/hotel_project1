package by.kanarski.booking.commands;

import by.kanarski.booking.constants.MessageConstants;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.utils.BookingSystemLogger;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommand implements ICommand {

    protected void handleServiceException(HttpServletRequest request, Exception exception) {
        String errorMessage = MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE);
        request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
        BookingSystemLogger.getInstance().logError(getClass(), errorMessage, exception);
    }

}
