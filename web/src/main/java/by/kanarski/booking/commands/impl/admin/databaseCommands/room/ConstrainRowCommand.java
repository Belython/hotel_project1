package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.OperationMessageKeys;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.Constraint;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ConstrainRowCommand extends AbstractCommand{

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);
        ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();

        RoomDto roomDto = RequestParser.parseRoomDto(request);
        Room room = DtoToEntityConverter.convertToRoom(roomDto, locale, currency);
        List<RoomType> roomTypeList = (List<RoomType>) session.getAttribute(Parameter.ROOM_TYPE_LIST);
        List<Hotel> hotelList = (List<Hotel>) session.getAttribute(Parameter.HOTEL_LIST);
        Map<String, Object> dataMap = new LinkedHashMap<>();
        Constraint.byHotel(dataMap, room.getRoomHotel(), hotelList);
        Constraint.byRoomType(dataMap, room.getRoomType(), roomTypeList, currency);
        dataMap.put(Parameter.ROOM_NUMBER, new HashSet<>());
        dataMap.put(Parameter.ROOM_STATUS, FieldValue.STATUS_LIST);
        request.setAttribute(Parameter.ROOM, roomDto);
        request.setAttribute(Parameter.DATA_MAP, dataMap);

        page = PagePath.TABLE_ROW_PATH;
        servletAction = ServletAction.NO_ACTION;
        servletAction.setPage(page);
        return servletAction;
    }
}
