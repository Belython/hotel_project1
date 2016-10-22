package by.kanarski.booking.filters;

import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.OperationMessageKeys;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        CommandType commandType = RequestParser.parseCommandType(httpServletRequest);
        if (commandType.name().equals(commandType.MAKEBILL.toString())) {
            UserDto userDto = (UserDto) session.getAttribute(Parameter.USER);
            if (userDto == null) {
                ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
                request.setAttribute(Parameter.OPERATION_MESSAGE, bundle.getString(OperationMessageKeys.AUTHORIZATION_ERRON));
                request.setAttribute(Parameter.COMMAND, "cancelAction");
            }
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
