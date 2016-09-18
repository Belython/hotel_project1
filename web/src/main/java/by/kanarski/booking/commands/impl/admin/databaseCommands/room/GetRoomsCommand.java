package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.utils.DtoToEntityConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


public class GetRoomsCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        try {
            User admin = (User) session.getAttribute(Parameter.USER);
            if (admin.getRole().equals(Role.ADMINISTRATOR)) {
                servletAction = ServletAction.FORWARD_PAGE;
                page = PagePath.ROOM_LIST_PAGE_PATH;
                // TODO: 10.09.2016 Зделать геттеры
                List<Room> roomList = RoomServiceImpl.getInstance().getAll();
                List<RoomDto> roomDtoList = covertToRoomDtoList(roomList, session);
                Set<Hotel> hotelSet = new HashSet<>();
                Set<RoomType> roomTypeSet = new HashSet<>();
                for (Room room: roomList) {
                    Hotel hotel = room.getRoomHotel();
                    RoomType roomType = room.getRoomType();
                    hotelSet.add(hotel);
                    roomTypeSet.add(roomType);
                }
                request.setAttribute(Parameter.ROOM_DTO_LIST, roomDtoList);
                session.setAttribute(Parameter.ROOM_LIST, roomList);
                session.setAttribute(Parameter.HOTEL_SET, hotelSet);
                session.setAttribute(Parameter.ROOM_TYPE_SET, roomTypeSet);
                session.setAttribute(Parameter.STATUS_LIST, Value.STATUS_LIST);
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

    private List<RoomDto> covertToRoomDtoList(List<Room> roomList, HttpSession session) {
        List<RoomDto> roomDtoList = new ArrayList<>();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        for (Room room : roomList) {
            RoomDto roomDto = DtoToEntityConverter.convertToRoomDto(room, locale);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }
}
