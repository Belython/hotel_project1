package by.kanarski.booking.filters;

import by.kanarski.booking.constants.OperationMessageKeys;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.services.impl.HotelServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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
                initialize(httpServletRequest);
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void initialize(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Set<String> supportedHotels = new HashSet<>();
        Set<String> supportedCountries = new HashSet<>();
        Set<String> supportedCities = new HashSet<>();
        try {
            List<HotelDto> allHotelDtos = HotelServiceImpl.getInstance().getAll();
            for (HotelDto hotelDto : allHotelDtos) {
                String hotelName = hotelDto.getHotelName();
                String country = hotelDto.getLocation().getCountry();
                String city = hotelDto.getLocation().getCity();
                supportedHotels.add(hotelName);
                supportedCountries.add(country);
                supportedCities.add(city);
            }
        } catch (ServiceException e) {
            Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
            ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
            String errorMessage = bundle.getString(OperationMessageKeys.ERROR_DATABASE);
            request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
        }
        session.setAttribute(Parameter.SUPPORTED_HOTELS, supportedHotels);
        session.setAttribute(Parameter.SUPPORTED_COUNTRIES, supportedCountries);
        session.setAttribute(Parameter.SUPPORTED_CITIES, supportedCities);
    }
}
