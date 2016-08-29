package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.MessageConstants;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.services.impl.RoomTypeServiceImpl;
import by.kanarski.booking.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class SelectHotelCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3000);
        try {
            OrderDto order = RequestParameterParser.getOrder(request);
            session.setAttribute(Parameter.ORDER, order);
            // TODO: 26.06.2016 ЭТО ВСЕ ВРЕМЕННО
            roomTypeFill();
            String hotelName = order.getHotel().getName();
            if (!hotelName.equals(Parameter.ANY_HOTEL)) {
                Hotel hotel = HotelServiceImpl.getInstance().getByHotelName(hotelName);
                session.setAttribute(Parameter.HOTEL, hotel);
                servletAction = ServletAction.CALL_COMMAND;
                servletAction.setCommandName(CommandType.SELECTROOM.name());
            } else {
                List<Room> availableRooms = RoomServiceImpl.getInstance().getAvailableRooms(order);
                session.setAttribute(Parameter.HOTEL_HOTELS_LIST, getHotels(availableRooms));
                page = PagePath.CLIENT_SELECT_HOTEL_PATH;
                servletAction = ServletAction.FORWARD_PAGE;
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

    private void roomTypeFill() throws ServiceException {
        List<RoomType> roomTypes = RoomTypeServiceImpl.getInstance().getAll();
        List<String> facilities = new ArrayList<>();
        facilities.add("wi-fi");
        facilities.add("safe");
        for (RoomType roomType : roomTypes) {
            roomType.setFacilities(facilities);
            RoomTypeServiceImpl.getInstance().update(roomType);
        }
    }

    private List<HotelDto> getHotels(List<Room> roomList) {
        List<HotelDto> hotelList = new ArrayList<>();
        int separator = 0;
        for (int i = 0; i < roomList.size(); i++) {
            if (i < (roomList.size() - 1)) {
                String curHotelName = roomList.get(i).getHotel().getName();
                String nextHotelName = roomList.get(i + 1).getHotel().getName();
                if (!curHotelName.equals(nextHotelName)) {
                    Hotel hotel = roomList.get(i).getHotel();
                    HotelDto hotelDto = new HotelDto(hotel.getId(), hotel.getCountry(), hotel.getCity(), hotel.getName(),
                            roomList.subList(separator, i + 1));
                    hotelList.add(hotelDto);
                    separator = i + 1;
                }
            } else {
                Hotel hotel = roomList.get(i).getHotel();
                HotelDto hotelDto = new HotelDto(hotel.getId(), hotel.getCountry(), hotel.getCity(), hotel.getName(),
                        roomList.subList(separator, i + 1));
                hotelList.add(hotelDto);
                separator = i + 1;
            }
        }
        return hotelList;
    }

}
