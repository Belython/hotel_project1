package by.kanarski.booking.commands.impl.admin;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Дмитрий on 02.09.2016.
 */
public class GoToAdminPageCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        User admin = (User) session.getAttribute(Parameter.USER);
        if (admin.getRole().equals(FieldValue.ROLE_ADMIN)) {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ADMIN_MAIN_PAGE_PATH;
        } else {
            request.setAttribute(Parameter.OPERATION_MESSAGE, "иди в жопу хакер сраный");
            servletAction = ServletAction.NO_ACTION;
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
