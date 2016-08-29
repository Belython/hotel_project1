package by.kanarski.booking.filters;

import by.kanarski.booking.constants.Attribute;
import by.kanarski.booking.constants.AttributeValue;

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
        Object localeList = request.getAttribute(Attribute.LOCALE_LIST);
        if (localeList == null) {
            request.setAttribute(Attribute.LOCALE_LIST, AttributeValue.LOCALE_LIST);
        }
        HttpSession session = httpServletRequest.getSession();
        Locale locale = (Locale) session.getAttribute(Attribute.LOCALE);
        if (locale == null) {
            locale = request.getLocale();
            session.setAttribute(Attribute.LOCALE, locale);
            request.setAttribute(Attribute.LOCALE, locale.getLanguage());
        }
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
