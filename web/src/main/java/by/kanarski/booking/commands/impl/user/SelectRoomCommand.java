package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class SelectRoomCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page;
        HttpSession session = request.getSession();
        try {
            List<Room> hotelRooms;
            OrderDto order = (OrderDto) session.getAttribute(Parameter.ORDER_DTO);
            Hotel hotel = order.getHotel();
            long hotelId = hotel.getHotelId();
            if (hotelId == FieldValue.UNDEFINED_ID) {
                hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_ID));
                hotel = HotelServiceImpl.getInstance().getById(hotelId);
                order.setHotel(hotel);
                session.setAttribute(Parameter.ORDER_DTO, order);
            }
            hotelRooms = RoomServiceImpl.getInstance().getAvailableRooms(order);
            HotelDto selectedHotelDto = new HotelDto(hotel.getHotelId(), hotel.getHotelLocation(), hotel.getHotelName(), hotelRooms);
            session.setAttribute(Parameter.HOTEL_SELECTED_HOTEL_DTO, selectedHotelDto);
            page = PagePath.CLIENT_SELECT_ROOM_PATH;
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            handleServiceException(request, e);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
