package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.Constrain;
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

        RoomDto roomDto = RequestParser.parseRoomDto(request);
//        Room room = DtoToEntityConverter.convertToRoom(roomDto, locale, currency);
        List<RoomType> roomTypeList = (List<RoomType>) session.getAttribute(Parameter.ROOM_TYPE_LIST);
        List<Hotel> hotelList = (List<Hotel>) session.getAttribute(Parameter.HOTEL_LIST);
        Map<String, Object> dataMap = new LinkedHashMap<>();
        dataMap.put(Parameter.ROOM_ID, new HashSet<>());
        HotelDto newHotelDto = Constrain.byHotel(dataMap, roomDto.getHotelDto(), hotelList);
        RoomTypeDto newRoomTypeDto = Constrain.byRoomType(dataMap, roomDto.getRoomTypeDto(), roomTypeList, currency);
        roomDto.setHotelDto(newHotelDto);
        roomDto.setRoomTypeDto(newRoomTypeDto);
        dataMap.put(Parameter.ROOM_NUMBER, new HashSet<>());
        dataMap.put(Parameter.ROOM_STATUS, FieldValue.STATUS_LIST);
        request.setAttribute(Parameter.ENTITY, roomDto);
        request.setAttribute(Parameter.DATA_MAP, dataMap);

        String formName = RequestParser.parseFormName(request);

        switch (formName) {
            case Value.NEW_ENTITY_FORM: {
                page = PagePath.NEW_TABLE_ROW_PATH;
                break;
            }
            case Value.ALTER_ENTITY_FORM: {
                page = PagePath.ALTER_TABLE_ROW_PATH;
                break;
            }
        }
        servletAction = ServletAction.NO_ACTION;
        servletAction.setPage(page);
        return servletAction;
    }
}
