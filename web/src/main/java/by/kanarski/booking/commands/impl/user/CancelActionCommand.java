package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelActionCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ServletAction servletAction;
        boolean isAjaxRequest = RequestParser.isAjaxRequest(request);
        if (isAjaxRequest) {
            servletAction = ServletAction.AJAX_REQUEST;
        } else {
            servletAction = ServletAction.FORWARD_PAGE;
            String page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        }
        return servletAction;
    }
}
