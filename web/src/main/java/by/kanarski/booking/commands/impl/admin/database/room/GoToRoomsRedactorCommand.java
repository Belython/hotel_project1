package by.kanarski.booking.commands.impl.admin.database.room;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.GlobalHotelDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.services.impl.RoomTypeServiceImpl;
import by.kanarski.booking.utils.ConstrainUtil;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.field.FieldDescriptor;
import by.kanarski.booking.utils.field.FieldBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;


public class GoToRoomsRedactorCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);
        ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
        try {
            User admin = (User) session.getAttribute(Parameter.USER);
            if (admin.getRole().equals(FieldValue.ROLE_ADMIN)) {
                servletAction = ServletAction.FORWARD_PAGE;
                page = PagePath.ROOMS_REDACTOR_PATH;
                List<Room> roomList = RoomServiceImpl.getInstance().getAll();
                List<RoomDto> roomDtoList = DtoToEntityConverter.toRoomDtoList(roomList, locale, currency);
                List<Hotel> hotelList = HotelServiceImpl.getInstance().getAll();
                List<RoomType> roomTypeList = RoomTypeServiceImpl.getInstance().getAll();

                List<FieldDescriptor<RoomDto>> descriptorList = new ArrayList<>();
                for (RoomDto roomDto: roomDtoList) {
                    LinkedHashMap<String, FieldDescriptor> roomFields = new LinkedHashMap<>();
                    FieldDescriptor roomIdFieldDescriptor = FieldBuilder.buildFreePrimitive();
                    FieldDescriptor<GlobalHotelDto> hotelDtoFieldDescriptor = ConstrainUtil.byHotel(roomDto.getRoomHotel(), hotelList);
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
                    descriptorList.add(roomDtoEntity);
                }

                session.setAttribute(Parameter.ALTER_TABLE_COMMAND, Value.ALTER_ROOMS);
                session.setAttribute(Parameter.DESCRIPTOR_LIST, descriptorList);
                session.setAttribute(Parameter.ROOM_LIST, roomList);
                session.setAttribute(Parameter.HOTEL_LIST, hotelList);
                session.setAttribute(Parameter.ROOM_TYPE_LIST, roomTypeList);
                session.setAttribute(Parameter.STATUS_LIST, FieldValue.STATUS_LIST);
            } else {
                String opertaionMessage = bundle.getString(OperationMessageKeys.LOW_ACCESS_LEVEL);
                request.setAttribute(Parameter.OPERATION_MESSAGE, opertaionMessage);
                servletAction = ServletAction.AJAX_REQUEST;
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
