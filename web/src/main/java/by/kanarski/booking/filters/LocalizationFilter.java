package by.kanarski.booking.filters;

import by.kanarski.booking.constants.Attribute;
import by.kanarski.booking.constants.AttributeValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class LocalizationFilter implements Filter {

    @Override
    public void init(FilterConfig encodingConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        Locale locale = (Locale) session.getAttribute(Attribute.LOCALE);
        if (locale == null) {
            locale = request.getLocale();
            session.setAttribute(Attribute.LOCALE, locale);
        }
        String currentPagePath = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        if (currentPagePath == null) {
            currentPagePath = PagePath.INDEX_PAGE_PATH;
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, currentPagePath);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, currentPagePath);
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
