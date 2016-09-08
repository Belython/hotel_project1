package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.MessageConstants;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
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
            Hotel hotel = (Hotel) session.getAttribute(Parameter.HOTEL);
            List<Room> hotelRooms;
            if (hotel == null) {
                long hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_SELECTED_HOTEL));
                hotelRooms = RoomServiceImpl.getInstance().getByHotelId(hotelId);
                hotel = hotelRooms.get(0).getHotel();
            } else {
                hotelRooms = RoomServiceImpl.getInstance().getByHotelId(hotel.getId());
            }

            HotelDto selectedHotelDto = new HotelDto(hotel.getId(), hotel.getLocation(), hotel.getName(), hotelRooms);
            session.setAttribute(Parameter.HOTEL_SELECTED_HOTEL, selectedHotelDto);
            page = PagePath.CLIENT_SELECT_ROOM_PATH;
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            handleServiceException(request, e);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }
}
