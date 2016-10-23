package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.GlobalHotelDto;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.GlobalHotelServiceImpl;
import by.kanarski.booking.services.impl.HotelServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GoToSelectRoomsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page;
        HttpSession session = request.getSession();
        try {
            OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER);
            HotelDto hotelDto = orderDto.getHotel();
            long hotelId = hotelDto.getHotelId();
            if (hotelId == FieldValue.UNDEFINED_ID) {
                hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_ID));
                hotelDto = HotelServiceImpl.getInstance().getById(hotelId);
                orderDto.setHotel(hotelDto);
                session.setAttribute(Parameter.ORDER, orderDto);
            }
            GlobalHotelDto selectedGlobalHotelDto = GlobalHotelServiceImpl.getInstance().getByOrder1(orderDto);
            session.setAttribute(Parameter.SELECTED_GLOBAL_HOTEL, selectedGlobalHotelDto);
            page = PagePath.SELECT_ROOM_PATH;
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
