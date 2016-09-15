package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Дмитрий on 01.09.2016.
 */
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
                String searchParameter = request.getParameter(Parameter.SEARCH_PARAMETER);
                String searchParameterValue = request.getParameter(Parameter.SEARCH_PARAMETER_VALUE);
                // TODO: 10.09.2016 Зделать геттеры
                List<Room> roomList = RoomServiceImpl.getInstance().getAll();
                Set<Hotel> hotelSet = new HashSet<>();
                Set<RoomType> roomTypeSet = new HashSet<>();
                for (Room room: roomList) {
                    Hotel hotel = room.getRoomHotel();
                    RoomType roomType = room.getRoomType();
                    hotelSet.add(hotel);
                    roomTypeSet.add(roomType);
                }


                session.setAttribute("roomList", roomList);
                session.setAttribute("hotelList", hotelSet);
                session.setAttribute("roomTypeList", roomTypeSet);
//                session.setAttribute(Parameter.FIELD_VALUES_MAP, fieldsValuesMap);
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

    private List<String> getJsonList(HttpServletRequest request, HttpSession session) throws ServiceException{
        String hotelCountry = request.getParameter(Parameter.HOTEL_COUNTRY);
        String hotelCity = request.getParameter(Parameter.HOTEL_CITY);
        List<Hotel> hotelList = HotelServiceImpl.getInstance().getAll();
        List<String> cityList = new ArrayList<>();
        for(Hotel hotel: hotelList) {
            Location hotelLocation = hotel.getHotelLocation();
            if (!hotelLocation.getCountry().equals(hotelCountry)) {
                continue;
            }
            cityList.add(hotelLocation.getCity());
        }
        return null;
    }

    public static void main(String[] args) throws ServiceException {
        List<Room> roomList = RoomServiceImpl.getInstance().getAll();
        Map<String, Set> fieldVariants = new HashMap<>();
        Set<Map<String, Object>> hotelVariants = new HashSet<>();
        Set<Map<String, Object>> roomTypeVariants = new HashSet<>();
        Set<Map<String, Object>> roomSet = new HashSet<>();
        for (Room room : roomList) {
            Hotel hotel = room.getRoomHotel();
            RoomType roomType = room.getRoomType();
            hotelVariants.add(getHotelMap(hotel));
            roomTypeVariants.add(getRoomTypeRepr(roomType));
            roomSet.add(getRoomMap(room));
        }
        fieldVariants.put("roomHotel", hotelVariants);
        fieldVariants.put("roomType", roomTypeVariants);
        Room room = RoomServiceImpl.getInstance().getById(1);
        Gson gson = new Gson();
        String str = gson.toJson(roomList);
        List<String> fieldsOrder = new ArrayList<>();
        fieldsOrder = Arrays.asList("roomHotel", "roomType", "roomNumber", "bookingStartDate",
                "bookingEndDate", "status");
        Map<String, Object> responeMap = new LinkedHashMap<>();
        responeMap.put("entityList", roomSet);
        responeMap.put("fieldMap", fieldVariants);

        String json = gson.toJson(responeMap);
        System.out.println("\n\n\n\n\n" + json + "\n\n\n\n\n\n");
    }

    private static Map<String, Object> getRoomTypeRepr(RoomType roomType) {
        Map<String, Object> roomTypeMap = new LinkedHashMap<>();
        roomTypeMap.put("roomTypeId", roomType.getRoomTypeId());
        roomTypeMap.put("roomTypeName", roomType.getRoomTypeName());
        roomTypeMap.put("mapPersons", roomType.getMaxPersons());
        roomTypeMap.put("roomPricePerNight", roomType.getRoomPricePerNight());
        roomTypeMap.put("facilities", roomType.getFacilities());
        return roomTypeMap;
    }

    private static Map<String, Object> getHotelMap(Hotel hotel) {
        Map<String, Object> hotelMap = new LinkedHashMap<>();
        hotelMap.put("hotelId", hotel.getHotelId());
        hotelMap.put("hotelCountry", hotel.getHotelLocation().getCountry());
        hotelMap.put("hotelCity", hotel.getHotelLocation().getCity());
        hotelMap.put("hotelName", hotel.getHotelName());
        return hotelMap;
    }

    private static Map<String, Object> getRoomMap(Room room) {
        Map<String, Object> roomMap = new LinkedHashMap<>();
        roomMap.put("roomId", room.getRoomId());
        roomMap.put("roomHotel", getHotelMap(room.getRoomHotel()));
        roomMap.put("roomType", getRoomTypeRepr(room.getRoomType()));
        roomMap.put("roomNumber", room.getRoomNumber());
        roomMap.put("bookingStartDate", room.getBookingStartDate());
        roomMap.put("bookingEndDate", room.getBookingEndDate());
        return roomMap;
    }

//    private Map<String, ?> convertToMap(Room room) {
//        Map<String, ?> roomMap = new HashMap<>();
//    }
//
//    private Map<String, > convertToMap(RoomType roomType) {
//        Map<String, > roomTypeMap = new HashMap<>();
//        String facilities = Arrays.toString(roomType.getFacilities());
//        roomTypeMap.put(Parameter.ROOM_TYPE_ID, roomType.getRoomTypeId());
//        roomTypeMap.put(Parameter.ROOM_TYPE_NAME, roomType.getRoomTypeName());
//        roomTypeMap.put(Parameter.ROOM_TYPE_MAX_PERSONS, roomType.getMaxPersons());
//        roomTypeMap.put(Parameter.ROOM_TYPE_PRICE_PER_NIGHT, roomType.getRoomPricePerNight());
//        roomTypeMap.put(Parameter.ROOM_TYPE_FACILITIES, facilities);
//        roomTypeMap.put(Parameter.ROOM_TYPE_STATUS, roomType.getRoomTypeStatus());
//        return roomTypeMap;
//    }
}
