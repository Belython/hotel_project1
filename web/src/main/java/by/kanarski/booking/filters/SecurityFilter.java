package by.kanarski.booking.filters;

import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SecurityFilter implements Filter {
    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession();
//        UserType type = RequestParser.getUserType(httpRequest);
        String type = null;
        try {
            CommandType commandType = RequestParser.parseCommandType(httpRequest);
            if (type == null) {
                if (commandType == CommandType.LOGIN) {
                    chain.doFilter(request, response);
                } else if (commandType == CommandType.GOTOREGISTRATION) {
                    chain.doFilter(request, response);
                } else if (commandType == CommandType.REGISTER) {
                    chain.doFilter(request, response);
                } else {
                    String page = PagePath.INDEX_PAGE_PATH;
                    RequestDispatcher dispatcher = request.getRequestDispatcher(page);
                    dispatcher.forward(httpRequest, httpResponse);
                    session.invalidate();
                }
            } else {
                chain.doFilter(request, response);
            }
        } catch (IllegalArgumentException e) {
            String page = PagePath.INDEX_PAGE_PATH;
            RequestDispatcher dispatcher = request.getRequestDispatcher(page);
            dispatcher.forward(httpRequest, httpResponse);
        }
    }

    public void destroy() {
    }
}
