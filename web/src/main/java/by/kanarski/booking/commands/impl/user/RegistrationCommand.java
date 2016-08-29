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

public class RegistrationCommand implements ICommand {
    private User user;

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        try {
            user = RequestParameterParser.parseUser(request);
//            accountIdString = request.getParameter(Parameter.ACCOUNT_ID);
            if (areFieldsFullStocked()) {
//                account = RequestParameterParser.getAccount(request);
                if (UserServiceImpl.getInstance().checkIsNewUser(user)) {
                    UserServiceImpl.getInstance().registrateUser(user);
                    page = PagePath.REGISTRATION_PAGE_PATH;
                    request.setAttribute(Parameter.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.SUCCESS_OPERATION));
                } else {
                    page = PagePath.REGISTRATION_PAGE_PATH;
                    request.setAttribute(Parameter.ERROR_USER_EXISTS, MessageManager.getInstance().getProperty(MessageConstants.USER_EXISTS));
                }
            } else {
                request.setAttribute(Parameter.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.EMPTY_FIELDS));
                page = PagePath.REGISTRATION_PAGE_PATH;
            }
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            request.setAttribute(Parameter.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
        } catch (NumberFormatException e) {
            request.setAttribute(Parameter.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.INVALID_NUMBER_FORMAT));
            page = PagePath.REGISTRATION_PAGE_PATH;
        }
        // TODO исправить
        catch (NullPointerException e) {
            page = PagePath.INDEX_PAGE_PATH;
            e.printStackTrace();
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

    // TODO javascript???
    private boolean areFieldsFullStocked() {

        boolean isFullStocked = false;
        if (!user.getFirstName().isEmpty()
                & !user.getLastName().isEmpty()
                & !user.getLogin().isEmpty()
                & !user.getPassword().isEmpty()
                & !user.getEmail().isEmpty()) {
            isFullStocked = true;
        }
        return isFullStocked;
    }
}
