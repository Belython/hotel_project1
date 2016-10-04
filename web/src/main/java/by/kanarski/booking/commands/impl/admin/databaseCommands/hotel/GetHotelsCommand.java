package by.kanarski.booking.commands.impl.admin.databaseCommands.hotel;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class GetHotelsCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        try {
            User admin = (User) session.getAttribute(Parameter.USER);
            if (admin.getRole().equals(FieldValue.ROLE_ADMIN)) {
                servletAction = ServletAction.FORWARD_PAGE;
                List<Hotel> hotelList = null;
                page = PagePath.HOTEL_LIST_PAGE_PATH;
                String searchParameter = request.getParameter(Parameter.FILTER_PARAMETER);
                String searchParameterValue = request.getParameter(Parameter.FILTER_PARAMETER_VALUE);
                switch (searchParameter) {
                    case Value.HOTEL_COUNTRY: {
                        hotelList = HotelServiceImpl.getInstance().getByCountry(searchParameterValue);
                        break;
                    }
                    case Value.HOTEL_CITY: {
                        hotelList = HotelServiceImpl.getInstance().getByCity(searchParameterValue);
                        break;
                    }
                    case Value.HOTEL_ALL_HOTELS: {
                        hotelList = HotelServiceImpl.getInstance().getAll();
                        break;
                    }
                }
                HashMap<String, Set<String>> fieldsValuesMap = HotelServiceImpl.getInstance().getFieldValues();
                session.setAttribute(Parameter.HOTEL_LIST, hotelList);
                session.setAttribute(Parameter.FIELD_VALUES_MAP, fieldsValuesMap);
            } else {
                request.setAttribute(Parameter.OPERATION_MESSAGE, "иди в жопу хакер сраный");
                servletAction = ServletAction.NO_ACTION;
            }
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            servletAction = ServletAction.REDIRECT_PAGE;
//            request.setAttribute(Parameter.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageKeys.ERROR_DATABASE));
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
