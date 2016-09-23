package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.MessageConstants;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.UserServiceImpl;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationCommand extends AbstractCommand {
    private User user;

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        try {
            user = RequestParser.parseUser(request);
//            accountIdString = request.getParameter(Parameter.ACCOUNT_ID);
            if (areFieldsFullStocked()) {
//                account = RequestParser.getAccount(request);
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
            handleServiceException(request, e);
        } catch (NumberFormatException e) {
            String exceptionMessage = MessageManager.getInstance().getProperty(MessageConstants.INVALID_NUMBER_FORMAT);
            request.setAttribute(Parameter.OPERATION_MESSAGE, exceptionMessage);
            page = PagePath.REGISTRATION_PAGE_PATH;
        }
        // TODO исправить
        catch (NullPointerException e) {
            page = PagePath.INDEX_PAGE_PATH;
            e.printStackTrace();
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
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
