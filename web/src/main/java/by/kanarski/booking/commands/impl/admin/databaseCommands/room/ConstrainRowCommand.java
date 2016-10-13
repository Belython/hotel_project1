package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.entities.RoomType;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.ConstrainUtil;
import by.kanarski.booking.utils.RequestParser;
import by.kanarski.booking.utils.field.Field;
import by.kanarski.booking.utils.field.FieldBuilder;

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
//        Map<String, Object> dataMap = new LinkedHashMap<>();

        LinkedHashMap<String, Field> roomFields = new LinkedHashMap<>();
        Field roomIdField = FieldBuilder.buildFreePrimitive();
        Field<HotelDto> hotelDtoField = ConstrainUtil.byHotel(roomDto.getRoomHotel(), hotelList);
        Field<RoomTypeDto> roomTypeDtoField = ConstrainUtil.byRoomType(roomDto.getRoomType(), roomTypeList, currency);
        roomDto.setRoomHotel(hotelDtoField.getOwner());
        roomDto.setRoomType(roomTypeDtoField.getOwner());
        Field roomNumberField = FieldBuilder.buildFreePrimitive();
        Field roomStatusField = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
        roomFields.put(Parameter.ROOM_ID, roomIdField);
        roomFields.put(Parameter.ROOM_HOTEL, hotelDtoField);
        roomFields.put(Parameter.ROOM_TYPE, roomTypeDtoField);
        roomFields.put(Parameter.ROOM_NUMBER, roomNumberField);
        roomFields.put(Parameter.ROOM_STATUS, roomStatusField);
        Field<RoomDto> roomDtoEntity = FieldBuilder.buildEntity(roomFields, roomDto);

        request.setAttribute(Parameter.DESCRIPTOR, roomDtoEntity);

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
