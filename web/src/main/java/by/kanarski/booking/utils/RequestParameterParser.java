package by.kanarski.booking.utils;

import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.entities.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class RequestParameterParser {
    private RequestParameterParser() {
    }

    public static User parseUser(ServletRequest request) {
        long id = -1;
        if (request.getParameter(Parameter.USER_ID) != null) {
            id = Long.valueOf(request.getParameter(Parameter.USER_ID));
        }
        String firstName = request.getParameter(Parameter.USER_FIRST_NAME);
        String lastName = request.getParameter(Parameter.USER_LAST_NAME);
        String email = request.getParameter(Parameter.USER_EMAIL);
        String login = request.getParameter(Parameter.USER_LOGIN);
        String password = request.getParameter(Parameter.USER_PASSWORD);
        String role = request.getParameter(Parameter.USER_ROLE);
        String status = request.getParameter(Parameter.USER_STATUS);
        User user = EntityBuilder.buildUser(id, firstName, lastName, email, login, password, role, status);
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
        long hotelId = -1;
        if (request.getParameter(Parameter.HOTEL_ID) != null) {
            hotelId = Long.valueOf(request.getParameter(Parameter.HOTEL_ID));
        }
        String country = request.getParameter(Parameter.LOCATION_COUNTRY);
        String city = request.getParameter(Parameter.LOCATION_CITY);
        String hotelName = request.getParameter(Parameter.HOTEL_NAME);
        Hotel hotel = EntityBuilder.buildHotel(hotelId, country, city, hotelName, "status");
        return hotel;
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

    public static Locale parseLocale(ServletRequest request) {
        String localeName = request.getParameter(Parameter.LOCALE);
        Locale locale = new Locale(localeName);
        return locale;
    }

    public static Bill parseBill(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Parameter.USER);
        OrderDto orderDto = (OrderDto) session.getAttribute(Parameter.ORDER);
        long checkInDate = orderDto.getCheckInDate();
        long checkOutDate = orderDto.getCheckOutDate();
        HotelDto hotelDto = (HotelDto) session.getAttribute(Parameter.HOTEL_SELECTED_HOTEL);
//        Hotel hotel = hotelDto.getHotel();
        int totalPersons = orderDto.getTotalPersons();
        List<RoomType> roomTypeList = hotelDto.getRoomTypeList();
        HashMap<RoomType, Integer> selectedRoomTypes = new HashMap<>();
        for (RoomType roomType : roomTypeList) {
            int roomTypeCount = Integer.valueOf(request.getParameter(roomType.getName()));
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

    private static int calc(int days, List<Room> roomList) {
        int payment = 0;
        for (Room room : roomList) {
            int part = room.getRoomType().getRoomPricePerNight() * days;
            payment += part;
        }
        return payment;
    }


}
