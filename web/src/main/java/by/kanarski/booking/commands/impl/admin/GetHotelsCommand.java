package by.kanarski.booking.commands.impl.admin;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.MessageConstants;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Role;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Дмитрий on 01.09.2016.
 */
public class GetHotelsCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        try {
            User admin = (User) session.getAttribute(Parameter.USER);
            if (admin.getRole().equals(Role.ADMINISTRATOR)) {
                servletAction = ServletAction.FORWARD_PAGE;
                List<Hotel> hotelList = HotelServiceImpl.getInstance().getAll();
                request.setAttribute(Parameter.HOTEL_HOTELS_LIST, hotelList);
                page = PagePath.HOTEL_LIST_PAGE_PATH;
            } else {
                request.setAttribute(Parameter.OPERATION_MESSAGE, "иди в жопу хакер сраный");
                servletAction = ServletAction.NO_ACTION;
            }
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            servletAction = ServletAction.REDIRECT_PAGE;
            request.setAttribute(Parameter.ERROR_DATABASE, MessageManager.getInstance().getProperty(MessageConstants.ERROR_DATABASE));
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
