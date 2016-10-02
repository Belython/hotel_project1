package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutUserCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        HttpSession session = request.getSession();
        session.removeAttribute(Parameter.USER);
        // TODO: 22.09.2016 небезопасно, при выходе оставляет кучу контента
//        String page = (String) request.getAttribute(Parameter.CURRENT_PAGE_PATH);
//        if (page == null) {
//            page = PagePath.INDEX_PAGE_PATH;
//        }
        String page = PagePath.INDEX_PAGE_PATH;
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
