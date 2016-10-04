package by.kanarski.booking.commands.impl.admin;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class GoToAdminPageCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
        User admin = (User) session.getAttribute(Parameter.USER);
        if (admin.getRole().equals(FieldValue.ROLE_ADMIN)) {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ADMIN_MAIN_PAGE_PATH;
        } else {
            String opertaionMessage = bundle.getString(OperationMessageKeys.LOW_ACCESS_LEVEL);
            request.setAttribute(Parameter.OPERATION_MESSAGE, opertaionMessage);
            servletAction = ServletAction.NO_ACTION;
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
