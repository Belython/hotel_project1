package by.kanarski.booking.commands.impl.admin.database.location;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GoToLocationsRedactorCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page = null;
//        HttpSession session = request.getSession();
//        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
//        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);
//        ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
//        try {
//            User admin = (User) session.getAttribute(Parameter.USER);
//            if (admin.getRole().equals(FieldValue.ROLE_ADMIN)) {
//                servletAction = ServletAction.FORWARD_PAGE;
//                page = PagePath.LOCATIONS_REDACTOR_PATH;
//                List<Location> locationList = LocationS.getInstance().getAll();
//                List<RoomTypeDto> roomTypeDtoList = DtoToEntityConverter.toRoomTypeDtoList(roomTypeList, currency);
//
//                List<FieldDescriptor<RoomTypeDto>> descriptorList = new ArrayList<>();
//                for (RoomTypeDto roomTypeDto: roomTypeDtoList) {
//                    LinkedHashMap<String, FieldDescriptor> roomTypeDescriptors = new LinkedHashMap<>();
//                    FieldDescriptor roomTypeIdDescriptor = FieldBuilder.buildFreePrimitive();
//                    FieldDescriptor roomTypeNameDescriptor = FieldBuilder.buildFreePrimitive();
//                    FieldDescriptor maxPersonsDescriptor = FieldBuilder.buildFreePrimitive();
//                    FieldDescriptor pricePerNightDescriptor = FieldBuilder.buildFreePrimitive();
//                    FieldDescriptor facilitiesDescriptor = FieldBuilder.buildFreePrimitive();
//                    FieldDescriptor roomTypeStatusDescriptor = FieldBuilder.buildPrimitive(FieldValue.STATUS_LIST);
//                    roomTypeDescriptors.put(Parameter.ROOM_TYPE_ID, roomTypeIdDescriptor);
//                    roomTypeDescriptors.put(Parameter.ROOM_TYPE_NAME, roomTypeNameDescriptor);
//                    roomTypeDescriptors.put(Parameter.ROOM_TYPE_MAX_PERSONS, maxPersonsDescriptor);
//                    roomTypeDescriptors.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, pricePerNightDescriptor);
//                    roomTypeDescriptors.put(Parameter.ROOM_TYPE_FACILITIES, facilitiesDescriptor);
//                    roomTypeDescriptors.put(Parameter.ROOM_TYPE_STATUS, roomTypeStatusDescriptor);
//                    FieldDescriptor<RoomTypeDto> roomTypeDtoDescriptor = FieldBuilder.buildEntity(roomTypeDescriptors, roomTypeDto);
//                    descriptorList.add(roomTypeDtoDescriptor);
//                }
//
//                session.setAttribute(Parameter.ALTER_TABLE_COMMAND, Value.ALTER_ROOM_TYPE);
//                session.setAttribute(Parameter.DESCRIPTOR_LIST, descriptorList);
//                session.setAttribute(Parameter.ROOM_TYPE_LIST, roomTypeDtoList);
//                session.setAttribute(Parameter.STATUS_LIST, FieldValue.STATUS_LIST);
//            } else {
//                String opertaionMessage = bundle.getString(OperationMessageKeys.LOW_ACCESS_LEVEL);
//                request.setAttribute(Parameter.OPERATION_MESSAGE, opertaionMessage);
//                servletAction = ServletAction.AJAX_REQUEST;
//            }
//        } catch (ServiceException e) {
//            page = PagePath.ERROR;
//            servletAction = ServletAction.FORWARD_PAGE;
//            String errorMessage = bundle.getString(OperationMessageKeys.ERROR_DATABASE);
//            request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
//        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return servletAction;
    }


}
