package by.kanarski.booking.filters;

import by.kanarski.booking.constants.MessageKeys;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.services.impl.HotelServiceImpl;
import by.kanarski.booking.utils.BookingSystemLogger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InitializationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        String currentPagePath = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        if (currentPagePath.equals(PagePath.INDEX_PAGE_PATH)) {
            Object supportedHotels = session.getAttribute(Parameter.SUPPORTED_HOTELS);
            if (supportedHotels == null)  {
                initialize(httpServletRequest, session);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void initialize(HttpServletRequest request, HttpSession session) {
        Set<String> supportedHotels = new HashSet<>();
        Set<String> supportedCountries = new HashSet<>();
        Set<String> supportedCities = new HashSet<>();
        try {
            List<Hotel> allHotels = HotelServiceImpl.getInstance().getAll();
            for (Hotel hotel : allHotels) {
                String hotelName = hotel.getHotelName();
                String country = hotel.getHotelLocation().getCountry();
                String city = hotel.getHotelLocation().getCity();
                supportedHotels.add(hotelName);
                supportedCountries.add(country);
                supportedCities.add(city);
            }
        } catch (ServiceException e) {
            String errorMessage = MessageManager.getInstance().getProperty(MessageKeys.ERROR_DATABASE);
            request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
            BookingSystemLogger.getInstance().logError(getClass(), errorMessage, e);
        }
        session.setAttribute(Parameter.SUPPORTED_HOTELS, supportedHotels);
        session.setAttribute(Parameter.SUPPORTED_COUNTRIES, supportedCountries);
        session.setAttribute(Parameter.SUPPORTED_CITIES, supportedCities);
    }
}
