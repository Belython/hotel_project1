package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.MessageKeys;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dao.impl.UserDao;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.mail.send.SendMailSSL;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class RemindPasswordCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        String email = request.getParameter(Parameter.USER_EMAIL);
        try {
            User user = UserServiceImpl.getInstance().getByEmail(email);
            String password = user.getPassword();
            SendMailSSL.getInstance().sendPassword(email, password, locale);
            ResourceBundle bundle = ResourceBuilder.MESSAGES.setLocale(locale).create();
            String operationResult = bundle.getString(MessageKeys.PASSWORD_SENT);
            request.setAttribute(Parameter.OPERATION_MESSAGE, operationResult);
            page = PagePath.INDEX_PAGE_PATH;
        }  catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            handleServiceException(request, e);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
