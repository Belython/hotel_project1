package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.services.impl.RoomTypeServiceImpl;
import by.kanarski.booking.utils.Constraint;
import by.kanarski.booking.utils.DtoToEntityConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


public class RedactRoomsCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
        try {
            User admin = (User) session.getAttribute(Parameter.USER);
            if (admin.getRole().equals(FieldValue.ROLE_ADMIN)) {
                servletAction = ServletAction.FORWARD_PAGE;
                page = PagePath.ROOMS_REDACTOR_PATH;
                List<Room> roomList = RoomServiceImpl.getInstance().getAll();
                List<RoomDto> roomDtoList = DtoToEntityConverter.covertToRoomDtoList(roomList, locale);
                List<Hotel> hotelList = HotelServiceImpl.getInstance().getAll();
                List<RoomType> roomTypeList = RoomTypeServiceImpl.getInstance().getAll();
                List<RoomTypeDto> roomTypeDtoList = DtoToEntityConverter.convertToRoomTypeDtoList(roomTypeList);

                Map<Long, Map<String, Object>> entityMap = new LinkedHashMap<>();
                for (Room room: roomList) {
                    Map<String, Object> dataMap = new LinkedHashMap<>();
                    Constraint.byHotel(dataMap, room.getRoomHotel(), hotelList);
                    Constraint.byRoomType(dataMap, room.getRoomType(), roomTypeList);
                    dataMap.put(Parameter.ROOM_NUMBER, new HashSet<>());
                    dataMap.put(Parameter.ROOM_STATUS, FieldValue.STATUS_LIST);
                    entityMap.put(room.getRoomId(), dataMap);
                }

                request.setAttribute(Parameter.ROOM_DTO_LIST, roomDtoList);
                request.setAttribute(Parameter.ENTITY_MAP, entityMap);
                session.setAttribute(Parameter.ROOM_LIST, roomList);
                session.setAttribute(Parameter.HOTEL_LIST, hotelList);
                session.setAttribute(Parameter.ROOM_TYPE_LIST, roomTypeList);
                session.setAttribute(Parameter.ROOM_TYPE_DTO_LIST, roomTypeDtoList);
                session.setAttribute(Parameter.STATUS_LIST, FieldValue.STATUS_LIST);
            } else {
                String opertaionMessage = bundle.getString(OperationMessageKeys.LOW_ACCESS_LEVEL);
                request.setAttribute(Parameter.OPERATION_MESSAGE, opertaionMessage);
                servletAction = ServletAction.NO_ACTION;
            }
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            servletAction = ServletAction.FORWARD_PAGE;
            String errorMessage = bundle.getString(OperationMessageKeys.ERROR_DATABASE);
            request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
