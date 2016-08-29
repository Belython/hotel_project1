package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelActionCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.NO_ACTION;
//        HttpSession session = request.getSession();
//        String page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
//        String tsst = request.getParameter(Parameter.COMMAND);
//        String tssst = (String) request.getAttribute(Parameter.COMMAND);
//        if (page == null) {
//            page = PagePath.INDEX_PAGE_PATH;
//        }
//        servletAction.setPage(page);
        return servletAction;
    }
}
