package by.kanarski.booking.filters;

import by.kanarski.booking.constants.PagePath;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageRedirectFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        // переход на стартовую страницу
        httpResponse.sendRedirect(httpRequest.getContextPath() + PagePath.INDEX_PAGE_PATH);
        chain.doFilter(request, response);
    }

    public void destroy() {
    }
}