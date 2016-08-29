package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.MessageConstants;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.UserServiceImpl;
import by.kanarski.booking.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUserCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        try {
            User user = RequestParameterParser.parseUser(request);
            if (UserServiceImpl.getInstance().checkUserAuthorization(user.getLogin(), user.getPassword())) {
                user = UserServiceImpl.getInstance().getUserByLogin(user.getLogin());
                session.setAttribute(Parameter.USER, user);
                page = RequestParameterParser.parsePagePath(request);
                if (page == null) {
                    page = PagePath.INDEX_PAGE_PATH;
                }
            } else {
                page = PagePath.INDEX_PAGE_PATH;
                request.setAttribute(Parameter.ERROR_LOGIN_OR_PASSWORD, MessageManager.getInstance().getProperty(MessageConstants.WRONG_LOGIN_OR_PASSWORD));
            }
            session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            request.setAttribute(Parameter.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
