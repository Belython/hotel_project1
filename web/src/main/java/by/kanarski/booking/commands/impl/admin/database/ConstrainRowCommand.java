package by.kanarski.booking.commands.impl.admin.database;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.ConstrainUtil;
import by.kanarski.booking.utils.RequestParser;
import by.kanarski.booking.utils.field.FieldBuilder;
import by.kanarski.booking.utils.field.FieldDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Currency;
import java.util.LinkedHashMap;
import java.util.List;

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
        servletAction = ServletAction.AJAX_REQUEST;
        servletAction.setPage(page);
        return servletAction;
    }

    private void constrainRoom(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);

        RoomDto roomDto = RequestParser.parseRoomDto(request);
        List<RoomTypeDto> roomTypeDtoList = (List<RoomTypeDto>) session.getAttribute(Parameter.ROOM_TYPE_LIST);
        List<HotelDto> hotelDtoList = (List<HotelDto>) session.getAttribute(Parameter.HOTEL_LIST);

        LinkedHashMap<String, FieldDescriptor> roomFields = new LinkedHashMap<>();
        FieldDescriptor roomIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor<HotelDto> hotelDtoFieldDescriptor = ConstrainUtil.byHotel(roomDto.getHotel(), hotelDtoList);
        FieldDescriptor<RoomTypeDto> roomTypeDtoFieldDescriptor = ConstrainUtil.byRoomType(roomDto.getRoomType(), roomTypeDtoList, currency);
        roomDto.setHotel(hotelDtoFieldDescriptor.getOwner());
        roomDto.setRoomType(roomTypeDtoFieldDescriptor.getOwner());
        FieldDescriptor roomNumberFieldDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor roomStatusFieldDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
        roomFields.put(Parameter.ROOM_ID, roomIdFieldDescriptor);
        roomFields.put(Parameter.ROOM_HOTEL, hotelDtoFieldDescriptor);
        roomFields.put(Parameter.ROOM_TYPE, roomTypeDtoFieldDescriptor);
        roomFields.put(Parameter.ROOM_NUMBER, roomNumberFieldDescriptor);
        roomFields.put(Parameter.ROOM_STATUS, roomStatusFieldDescriptor);
        FieldDescriptor<RoomDto> roomDtoDescriptor = FieldBuilder.buildEntity(roomFields, roomDto);

        request.setAttribute(Parameter.DESCRIPTOR, roomDtoDescriptor);
    }

    private void constrainRoomType(HttpServletRequest request) {
        RoomTypeDto roomTypeDto = RequestParser.parseRoomTypeDto(request);

        LinkedHashMap<String, FieldDescriptor> roomTypeDescriptors = new LinkedHashMap<>();
        FieldDescriptor roomTypeIdDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor roomTypeNameDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor maxPersonsDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor pricePerNightDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor facilitiesDescriptor = FieldBuilder.buildFreePrimitive();
        FieldDescriptor roomTypeStatusDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
        roomTypeDescriptors.put(Parameter.ROOM_TYPE_ID, roomTypeIdDescriptor);
        roomTypeDescriptors.put(Parameter.ROOM_TYPE_NAME, roomTypeNameDescriptor);
        roomTypeDescriptors.put(Parameter.ROOM_TYPE_MAX_PERSONS, maxPersonsDescriptor);
        roomTypeDescriptors.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, pricePerNightDescriptor);
        roomTypeDescriptors.put(Parameter.ROOM_TYPE_FACILITIES, facilitiesDescriptor);
        roomTypeDescriptors.put(Parameter.ROOM_TYPE_STATUS, roomTypeStatusDescriptor);
        FieldDescriptor<RoomTypeDto> roomTypeDtoDescriptor = FieldBuilder.buildEntity(roomTypeDescriptors, roomTypeDto);

        request.setAttribute(Parameter.DESCRIPTOR, roomTypeDtoDescriptor);
    }
}
