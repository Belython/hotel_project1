package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GoBackCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        HttpSession session = request.getSession();
        String page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        if (page == null) {
            page = PagePath.INDEX_PAGE_PATH;
            session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        }
        servletAction.setPage(page);
        return servletAction;
    }
}
