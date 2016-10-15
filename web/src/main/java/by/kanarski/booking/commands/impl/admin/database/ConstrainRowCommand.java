package by.kanarski.booking.commands.impl.admin.database;

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
import by.kanarski.booking.utils.field.FieldDescriptor;
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

        String currentPagePath = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        switch (currentPagePath) {
            case PagePath.ROOMS_REDACTOR_PATH: {
                constrainRoom(request);
                break;
            }
            case PagePath.ROOM_TYPE_REDACTOR_PATH: {
                constrainRoomType(request);
            }
        }

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

    private void constrainRoom(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);

        RoomDto roomDto = RequestParser.parseRoomDto(request);
        List<RoomType> roomTypeList = (List<RoomType>) session.getAttribute(Parameter.ROOM_TYPE_LIST);
        List<Hotel> hotelList = (List<Hotel>) session.getAttribute(Parameter.HOTEL_LIST);

        LinkedHashMap<String, FieldDescriptor> roomFields = new LinkedHashMap<>();
        FieldDescriptor roomIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<HotelDto> hotelDtoFieldDescriptor = ConstrainUtil.byHotel(roomDto.getRoomHotel(), hotelList);
        FieldDescriptor<RoomTypeDto> roomTypeDtoFieldDescriptor = ConstrainUtil.byRoomType(roomDto.getRoomType(), roomTypeList, currency);
        roomDto.setRoomHotel(hotelDtoFieldDescriptor.getOwner());
        roomDto.setRoomType(roomTypeDtoFieldDescriptor.getOwner());
        FieldDescriptor roomNumberFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor roomStatusFieldDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
        roomFields.put(Parameter.ROOM_ID, roomIdFieldDescriptor);
        roomFields.put(Parameter.ROOM_HOTEL, hotelDtoFieldDescriptor);
        roomFields.put(Parameter.ROOM_TYPE, roomTypeDtoFieldDescriptor);
        roomFields.put(Parameter.ROOM_NUMBER, roomNumberFieldDescriptor);
        roomFields.put(Parameter.ROOM_STATUS, roomStatusFieldDescriptor);
        FieldDescriptor<RoomDto> roomDtoEntity = FieldBuilder.buildEntity(roomFields, roomDto);

        request.setAttribute(Parameter.DESCRIPTOR, roomDtoEntity);
    }

    private void constrainRoomType(HttpServletRequest request) {

    }
}
