package by.kanarski.booking.utils;

import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestParser {
    private RequestParser() {
    }

    public static User parseUser(ServletRequest request) {
//        long id = -1;
//        if (request.getParameter(Parameter.USER_ID) != null) {
//            id = Long.valueOf(request.getParameter(Parameter.USER_ID));
//        }
        String firstName = request.getParameter(Parameter.USER_FIRST_NAME);
        String lastName = request.getParameter(Parameter.USER_LAST_NAME);
        String email = request.getParameter(Parameter.USER_EMAIL);
        String login = request.getParameter(Parameter.USER_LOGIN);
        String password = request.getParameter(Parameter.USER_PASSWORD);
        String role = request.getParameter(Parameter.USER_ROLE);
//        String status = request.getParameter(Parameter.USER_STATUS);
        User user = EntityBuilder.buildUser(firstName, lastName, email, login, password, role);
        return user;
    }

    public static OrderDto getOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale currentLocale = (Locale) session.getAttribute(Parameter.LOCALE);
        User user = (User) session.getAttribute(Parameter.USER);
        int totalPersons = Integer.valueOf(request.getParameter(Parameter.ORDER_TOTAL_PERSONS));
        Hotel hotel = parseHotel(request);
        long checkInDate = LocalizationUtil.parseDate(request.getParameter(Parameter.ORDER_CHECK_IN_DATE), currentLocale);
        long checkOutDate = LocalizationUtil.parseDate(request.getParameter(Parameter.ORDER_CHECK_OUT_DATE), currentLocale);
        OrderDto orderDto = new OrderDto(user, hotel, totalPersons, checkInDate, checkOutDate);
        return orderDto;
    }

    public static Hotel parseHotel(ServletRequest request) {
//        long hotelId = -1;
//        if (request.getParameter(Parameter.HOTEL_ID) != null) {
//            hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_ID));
//        }
        String country = request.getParameter(Parameter.LOCATION_COUNTRY);
        String city = request.getParameter(Parameter.LOCATION_CITY);
        String hotelName = request.getParameter(Parameter.HOTEL_NAME);
        Hotel hotel = EntityBuilder.buildHotel(country, city, hotelName);
        return hotel;
    }

    public static List<Hotel> parseHotelList(ServletRequest request) {
        List<Hotel> hotelList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] hotelIdArray = null;
        String[] hotelNameArray = null;
        String[] hotelStatusArray = null;
        String[] hotelCityArray = null;
        String[] hotelCountryArray = null;
        for (String parameter : parameterSet) {
            switch (parameter) {
                case Parameter.HOTEL_ID: {
                    hotelIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.HOTEL_NAME: {
                    hotelNameArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.HOTEL_STATUS: {
                    hotelStatusArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.HOTEL_CITY: {
                    hotelCityArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.HOTEL_COUNTRY: {
                    hotelCountryArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        for (int i = 0; i < hotelIdArray.length; i++) {
            Hotel hotel = EntityBuilder.buildHotel(Long.valueOf(hotelIdArray[i]), hotelCountryArray[i], hotelCityArray[i], hotelNameArray[i], hotelStatusArray[i]);
            hotelList.add(hotel);
        }

        return hotelList;
    }

    public static String parsePagePath(ServletRequest request) {
        String page = request.getParameter(Parameter.CURRENT_PAGE_PATH);
        return page;
    }

    public static CommandType parseCommandType(HttpServletRequest request) {
        String commandName = (String) request.getAttribute(Parameter.COMMAND);
        if (commandName != null) {
            request.removeAttribute(Parameter.COMMAND);
        } else {
            commandName = request.getParameter(Parameter.COMMAND);
        }
        CommandType commandType = CommandType.LOGIN;
        if (commandName != null) {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        }
        return commandType;
    }

    public static Locale parseLocale(HttpServletRequest request) {
        String localeName = request.getParameter(Parameter.LOCALE);
        Locale locale = null;
        if (localeName == null) {
            HttpSession session = request.getSession();
            locale = (Locale) session.getAttribute(Parameter.LOCALE);
        } else {
            String fullLocaleRegexp = "[a-z]+_[A-Z]+";
            String language = null;
            String country = null;
            if (localeName.matches(fullLocaleRegexp)) {
                String languageOrCountryRegexp = "[a-zA-Z]+";
                Pattern pattern = Pattern.compile(languageOrCountryRegexp);
                Matcher matcher = pattern.matcher(localeName);
                matcher.find();
                language = matcher.group();
                matcher.find();
                country = matcher.group();
                locale = new Locale(language, country);
            } else {
                locale = new Locale(localeName);
            }
        }
        return locale;
    }

    public static Bill parseBill(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameter.USER);
        OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER);
        long checkInDate = orderDto.getCheckInDate();
        long checkOutDate = orderDto.getCheckOutDate();
        HotelDto hotelDto = (HotelDto) session.getAttribute(Parameter.HOTEL_SELECTED_HOTEL);
//        Hotel hotel = extendedHotel.getRoomHotel();
        int totalPersons = orderDto.getTotalPersons();
        List<RoomType> roomTypeList = hotelDto.getRoomTypeList();
        HashMap<RoomType, Integer> selectedRoomTypes = new HashMap<>();
        for (RoomType roomType : roomTypeList) {
            int roomTypeCount = Integer.valueOf(request.getParameter(roomType.getRoomTypeName()));
            if (roomTypeCount != 0) {
                selectedRoomTypes.put(roomType, roomTypeCount);
            }
        }
        List<Room> selectedRooms = AdminLogic.chooseRoomList(selectedRoomTypes, hotelDto.getRoomList());
        int paymentAmount = calc(LocalizationUtil.getDays(checkInDate, checkOutDate), selectedRooms);
        Bill bill = EntityBuilder.buildNewBill(user, totalPersons, checkInDate, checkOutDate, selectedRooms,
                paymentAmount);
        return bill;
    }

//    public static List<Room> parseRoomList(HttpServletRequest request) {
//        List<Room> roomList = new ArrayList<>();
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        Set<String> parameterSet = parameterMap.keySet();
//        String[] roomIdArray = null;
//        String[] hotelIdArray = null;
//        String[] roomTypeIdArray = null;
//        String[] roomNumberArray = null;
//        String[] bookingStartDateArray = null;
//        String[] bookingEndDateArray = null;
//        String[] roomStatusArray = null;
//        for (String parameter : parameterSet) {
//            switch (parameter) {
//                case Parameter.ROOM_ID: {
//                    roomIdArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.HOTEL_ID: {
//                    hotelIdArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.ROOM_TYPE_ID: {
//                    roomTypeIdArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.ROOM_NUMBER: {
//                    roomNumberArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.ROOM_BOOKING_START_DATE: {
//                    bookingStartDateArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.ROOM_BOOKING_END_DATE: {
//                    bookingEndDateArray = parameterMap.get(parameter);
//                    break;
//                }
//                case Parameter.ROOM_STATUS: {
//                    roomStatusArray = parameterMap.get(parameter);
//                    break;
//                }
//            }
//        }
//        HttpSession session = request.getSession();
//        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
//        for (int i = 0; i < roomIdArray.length; i++) {
//            Set<Hotel> hotelSet = (Set<Hotel>) session.getAttribute(Parameter.HOTEL_SET);
//            Set<RoomType> roomTypeSet = (Set<RoomType>) session.getAttribute(Parameter.ROOM_TYPE_SET);
//            long roomId = Long.valueOf(roomIdArray[i]);
//            long hotelId = Long.valueOf(hotelIdArray[i]);
//            long roomTypelId = Long.valueOf(roomTypeIdArray[i]);
//            int roomNumber = Integer.valueOf(roomNumberArray[i]);
//            long bookingStartDate = LocalizationUtil.parseDate(bookingStartDateArray[i], locale);
//            long bookingEndDate = LocalizationUtil.parseDate(bookingEndDateArray[i], locale);
//            Hotel neededHotel = null;
//            RoomType neededRoomType = null;
//            for (Hotel hotel : hotelSet) {
//                if (hotel.getHotelId() == hotelId) {
//                    neededHotel = hotel;
//                }
//            }
//            for (RoomType roomType : roomTypeSet) {
//                if (roomType.getRoomTypeId() == roomTypelId) {
//                    neededRoomType = roomType;
//                }
//            }
//            Room room = EntityBuilder.buildRoom(roomId, neededHotel, neededRoomType, roomNumber,
//                    bookingStartDate, bookingEndDate, roomStatusArray[i]);
//            roomList.add(room);
//        }
//        return roomList;
//    }

    public static List<RoomDto> parseRoomDtoList(HttpServletRequest request) {
        List<RoomDto> roomDtoList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] roomIdArray = null;
        String[] hotelIdArray = null;
        String[] roomTypeIdArray = null;
        String[] roomNumberArray = null;
        String[] bookingStartDateArray = null;
        String[] bookingEndDateArray = null;
        String[] roomStatusArray = null;
        for (String parameter : parameterSet) {
            switch (parameter) {
                case Parameter.ROOM_ID: {
                    roomIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.HOTEL_ID: {
                    hotelIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_ID: {
                    roomTypeIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_NUMBER: {
                    roomNumberArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_BOOKING_START_DATE: {
                    bookingStartDateArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_BOOKING_END_DATE: {
                    bookingEndDateArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_STATUS: {
                    roomStatusArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        HttpSession session = request.getSession();
        for (int i = 0; i < roomIdArray.length; i++) {
            Set<Hotel> hotelSet = (Set<Hotel>) session.getAttribute(Parameter.HOTEL_SET);
            Set<RoomType> roomTypeSet = (Set<RoomType>) session.getAttribute(Parameter.ROOM_TYPE_SET);
            long roomId = Long.valueOf(roomIdArray[i]);
            long hotelId = Long.valueOf(hotelIdArray[i]);
            long roomTypelId = Long.valueOf(roomTypeIdArray[i]);
            int roomNumber = Integer.valueOf(roomNumberArray[i]);
            String bookingStartDate = bookingStartDateArray[i];
            String bookingEndDate = bookingEndDateArray[i];
            Hotel neededHotel = null;
            RoomType neededRoomType = null;
            for (Hotel hotel : hotelSet) {
                if (hotel.getHotelId() == hotelId) {
                    neededHotel = hotel;
                }
            }
            for (RoomType roomType : roomTypeSet) {
                if (roomType.getRoomTypeId() == roomTypelId) {
                    neededRoomType = roomType;
                }
            }
            RoomDto roomDto = DtoBuilder.buildRoomDto(roomId, neededHotel, neededRoomType, roomNumber,
                    bookingStartDate, bookingEndDate, roomStatusArray[i]);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {String stringValue = request.getParameter(Parameter.IS_AJAX_REQUEST);
        boolean isAjaxRequest = Boolean.parseBoolean(stringValue);
        return isAjaxRequest;
    }

    private static int calc(int days, List<Room> roomList) {
        int payment = 0;
        for (Room room : roomList) {
            int part = room.getRoomType().getRoomPricePerNight() * days;
            payment += part;
        }
        return payment;
    }

}
