package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.OperationMessageKeys;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.UserServiceImpl;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginUserCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
//        Locale locale = UserPreferences.getLocale();
        try {
            UserDto user = RequestParser.parseUserDto(request);
            boolean isAuthorised = UserServiceImpl.getInstance().checkAuthorization(user.getLogin(), user.getPassword());
            if (isAuthorised) {
                user = UserServiceImpl.getInstance().getByLogin(user.getLogin());
                session.setAttribute(Parameter.USER, user);
                page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
                if (page == null) {
                    page = PagePath.INDEX_PAGE_PATH;
                }
            } else {
                page = PagePath.INDEX_PAGE_PATH;
                ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
                String errorMessage = bundle.getString(OperationMessageKeys.WRONG_LOGIN_OR_PASSWORD);
                request.setAttribute(Parameter.OPERATION_MESSAGE, errorMessage);
            }
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
