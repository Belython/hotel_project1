package by.kanarski.booking.utils;

import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.dto.GlobalHotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.dto.RoomTypeDto;
import by.kanarski.booking.entities.*;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.services.impl.RoomServiceImpl;

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

    public static OrderDto parseOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale currentLocale = (Locale) session.getAttribute(Parameter.LOCALE);
        User user = (User) session.getAttribute(Parameter.USER);
        int totalPersons = Integer.valueOf(request.getParameter(Parameter.ORDER_TOTAL_PERSONS));
        Hotel hotel = parseHotel(request);
        long checkInDate = DateUtil.parseDate(request.getParameter(Parameter.ORDER_CHECK_IN_DATE), currentLocale);
        long checkOutDate = DateUtil.parseDate(request.getParameter(Parameter.ORDER_CHECK_OUT_DATE), currentLocale);
        OrderDto orderDto = new OrderDto(user, hotel, totalPersons, checkInDate, checkOutDate);
        return orderDto;
    }

    public static Hotel parseHotel(ServletRequest request) {
        long hotelId = FieldValue.UNDEFINED_ID;
        if (request.getParameter(Parameter.HOTEL_ID) != null) {
            hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_ID));
        }
        String country = request.getParameter(Parameter.LOCATION_COUNTRY);
        String city = request.getParameter(Parameter.LOCATION_CITY);
        String hotelName = request.getParameter(Parameter.HOTEL_NAME);
        Hotel hotel = EntityBuilder.buildHotel(hotelId, country, city, hotelName);
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

    public static Currency parseCurrency(HttpServletRequest request) {
        String currencyCode = request.getParameter(Parameter.CURRENCY);
        Currency currency = null;
        if (currencyCode == null) {
            HttpSession session = request.getSession();
            currency = (Currency) session.getAttribute(Parameter.CURRENCY);
        } else {
            currency = Currency.getInstance(currencyCode);
        }
        return currency;
    }

    public static Bill parseBill(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameter.USER);
        OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER_DTO);
        long checkInDate = orderDto.getCheckInDate();
        long checkOutDate = orderDto.getCheckOutDate();
        GlobalHotelDto globalHotelDto = (GlobalHotelDto) session.getAttribute(Parameter.HOTEL_SELECTED_HOTEL_DTO);
        int totalPersons = orderDto.getTotalPersons();
        Set<RoomType> roomTypeSet = globalHotelDto.getRoomTypesCount().keySet();
        HashMap<RoomType, Integer> selectedRoomTypes = new HashMap<>();
        for (RoomType roomType : roomTypeSet) {
            int roomTypeCount = Integer.valueOf(request.getParameter(roomType.getRoomTypeName()));
            if (roomTypeCount != 0) {
                selectedRoomTypes.put(roomType, roomTypeCount);
            }
        }
        List<Room> selectedRooms = AdminLogic.chooseRoomList(selectedRoomTypes, globalHotelDto.getRoomList());
        double paymentAmount = calc(getBookedDays(checkInDate, checkOutDate), selectedRooms);
        Bill bill = EntityBuilder.buildNewBill(user, totalPersons, checkInDate, checkOutDate, selectedRooms,
                paymentAmount);
        return bill;
    }

    // TODO: 19.10.2016 куда-нибудь скомпоновать

    private static int getBookedDays(long date1, long date2) {
        final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
        int days = Math.round((date2 - date1) / MILLISECONDS_IN_DAY);
        return days;
    }

    public static List<RoomDto> parseRoomDtoList(HttpServletRequest request) throws ServiceException{
        List<RoomDto> roomDtoList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] roomIdArray = null;
        String[] hotelIdArray = null;
        String[] roomTypeIdArray = null;
        String[] roomNumberArray = null;
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
                case Parameter.ROOM_STATUS: {
                    roomStatusArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        HttpSession session = request.getSession();
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);
        for (int i = 0; i < roomIdArray.length; i++) {
            List<Hotel> hotelSet = (List<Hotel>) session.getAttribute(Parameter.HOTEL_LIST);
            List<RoomType> roomTypeSet = (List<RoomType>) session.getAttribute(Parameter.ROOM_TYPE_LIST);
            long roomId = Long.valueOf(roomIdArray[i]);
            long hotelId = Long.valueOf(hotelIdArray[i]);
            long roomTypelId = Long.valueOf(roomTypeIdArray[i]);
            int roomNumber = Integer.valueOf(roomNumberArray[i]);
            Hotel requestedHotel = null;
            RoomType requestedRoomType = null;
            for (Hotel hotel : hotelSet) {
                if (hotel.getHotelId() == hotelId) {
                    requestedHotel = hotel;
                }
            }
            for (RoomType roomType : roomTypeSet) {
                if (roomType.getRoomTypeId() == roomTypelId) {
                    requestedRoomType = roomType;

                }
            }
            RoomTypeDto requestedRoomTypeDto = DtoToEntityConverter.toRoomTypeDto(requestedRoomType, currency);
            GlobalHotelDto requestedGlobalHotelDto = DtoToEntityConverter.toHotelDto(requestedHotel);
            // TODO: 25.09.2016 Не хочу заморачиваться с датами номера, буду получать их из базы данных
            TreeMap<String, String> bookedDates = likeParseBookedDates(roomId, request);
            RoomDto roomDto = new RoomDto(roomId, requestedGlobalHotelDto, requestedRoomTypeDto, roomNumber, bookedDates, roomStatusArray[i]);
            roomDtoList.add(roomDto);
        }
        return roomDtoList;
    }

    public static List<RoomTypeDto> parseRoomTypeDtoList(HttpServletRequest request) throws ServiceException{
        List<RoomTypeDto> roomTypeDtoList = new ArrayList<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> parameterSet = parameterMap.keySet();
        String[] roomTypeIdArray = null;
        String[] roomTypeNameArray = null;
        String[] maxPersonsArray = null;
        String[] pricePerNightArray = null;
        String[] facilitiesArray = null;
        String[] roomTypeStatusArray = null;
        for (String parameter : parameterSet) {
            switch (parameter) {
                case Parameter.ROOM_TYPE_ID: {
                    roomTypeIdArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_NAME: {
                    roomTypeNameArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_MAX_PERSONS: {
                    maxPersonsArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_PRICE_PER_NIGHT: {
                    pricePerNightArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_FACILITIES: {
                    facilitiesArray = parameterMap.get(parameter);
                    break;
                }
                case Parameter.ROOM_TYPE_STATUS: {
                    roomTypeStatusArray = parameterMap.get(parameter);
                    break;
                }
            }
        }
        for (int i = 0; i < roomTypeIdArray.length; i++) {
            long roomTypeId = Long.valueOf(roomTypeIdArray[i]);
            String roomTypeName = roomTypeNameArray[i];
            int maxPersons = Integer.valueOf(maxPersonsArray[i]);
            double pricePerNight = Double.valueOf(pricePerNightArray[i]);
            String facilities = facilitiesArray[i];
            String roomTypeStatus = roomTypeStatusArray[i];
            RoomTypeDto roomTypeDto = new RoomTypeDto(roomTypeId, roomTypeName, maxPersons, pricePerNight,
                    facilities, roomTypeStatus);
            roomTypeDtoList.add(roomTypeDto);
        }
        return roomTypeDtoList;
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {String stringValue = request.getParameter(Parameter.IS_AJAX_REQUEST);
        boolean isAjaxRequest = Boolean.parseBoolean(stringValue);
        return isAjaxRequest;
    }

    private static double calc(int days, List<Room> roomList) {
        double payment = 0;
        for (Room room : roomList) {
            double part = room.getRoomType().getPricePerNight() * days;
            payment += part;
        }
        return payment;
    }

    public static RoomTypeDto parseRoomTypeDto(HttpServletRequest request) {
        long roomTypeId = Long.valueOf(request.getParameter(Parameter.ROOM_TYPE_ID));
        String roomTypeName = request.getParameter(Parameter.ROOM_TYPE_NAME);
        int maxPersons = Integer.valueOf(request.getParameter(Parameter.ROOM_TYPE_MAX_PERSONS));
        double pricePerNight = Double.valueOf(request.getParameter(Parameter.ROOM_TYPE_PRICE_PER_NIGHT));
        String facilities = request.getParameter(Parameter.ROOM_TYPE_FACILITIES);
        String roomTypeStatus = request.getParameter(Parameter.ROOM_TYPE_STATUS);
        RoomTypeDto roomTypeDto = new RoomTypeDto(roomTypeId, roomTypeName, maxPersons, pricePerNight,
                facilities, roomTypeStatus);
        return roomTypeDto;
    }

    public static RoomDto parseRoomDto(HttpServletRequest request) {
        String strRoomId = request.getParameter(Parameter.ROOM_ID);
        if ((strRoomId == null) || (strRoomId.isEmpty())) {
            strRoomId = "-1";
        }
        long roomId = Long.valueOf(strRoomId);
        Hotel hotel = parseHotel(request);
        RoomTypeDto roomTypeDto = parseRoomTypeDto(request);
        String strRoomNumber = request.getParameter(Parameter.ROOM_NUMBER);
        if ((strRoomNumber == null) || (strRoomNumber.isEmpty())) {
            strRoomNumber = "-1";
        }
        int roomNumber = Integer.valueOf(strRoomNumber);
        TreeMap<String, String> bookedDates = likeParseBookedDates(roomId, request);
        String roomStatus = request.getParameter(Parameter.ROOM_STATUS);
        GlobalHotelDto globalHotelDto = DtoToEntityConverter.toHotelDto(hotel);
        RoomDto roomDto = new RoomDto(roomId, globalHotelDto, roomTypeDto, roomNumber, bookedDates, roomStatus);
        return roomDto;
    }

    public static String parseFormName(HttpServletRequest request) {
        String formName = request.getParameter(Parameter.FORM_NAME);
        return formName;
    }

    //В UI не работаем с bookedDates, берем из базы данных
    private static TreeMap<String, String> likeParseBookedDates(long roomId, HttpServletRequest request){
        HttpSession session = request.getSession();
        TreeMap<String, String> bookedDates;
        if (roomId != FieldValue.UNDEFINED_ID) {
            Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
            Room room = null;
            try {
                room = RoomServiceImpl.getInstance().getById(roomId);
            }
            catch (ServiceException e) {
                e.printStackTrace();
            }
            bookedDates = DtoToEntityConverter.localizeBookedDates(room.getBookedDates(), locale);
        } else {
            bookedDates = new TreeMap<>();
        }
        return bookedDates;
    }
}
