package by.kanarski.booking.filters;


import javax.servlet.*;
import java.io.IOException;


public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig encodingConfig) throws ServletException {
        encoding = encodingConfig.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setCharacterEncoding(encoding);
        next.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
