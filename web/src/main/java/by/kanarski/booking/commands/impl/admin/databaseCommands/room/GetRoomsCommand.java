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
                List<Hotel> hotelList = null;
                page = PagePath.ROOM_LIST_PAGE_PATH;
                String searchParameter = request.getParameter(Parameter.SEARCH_PARAMETER);
                String searchParameterValue = request.getParameter(Parameter.SEARCH_PARAMETER_VALUE);
                // TODO: 10.09.2016 Зделать геттеры
                List<Room> roomList = RoomServiceImpl.getInstance().getAll();
                Set<Hotel> hotelSet = new HashSet<>();
                Set<RoomType> roomTypeSet = new HashSet<>();

                for (Room room: roomList) {
                    Hotel hotel = room.getHotel();
                    RoomType roomType = room.getRoomType();
                    hotelSet.add(hotel);
                    roomTypeSet.add(roomType);
                }

//                HashMap<String, Set<String>> fieldsValuesMap = HotelServiceImpl.getInstance().getFieldValues();
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
            Location hotelLocation = hotel.getLocation();
            if (!hotelLocation.getCountry().equals(hotelCountry)) {
                continue;
            }
            cityList.add(hotelLocation.getCity());
        }
        return null;
    }

//    public static void main(String[] args) throws ServiceException {
//        List<Room> roomList = RoomServiceImpl.getInstance().getAll();
//        Room room = RoomServiceImpl.getInstance().getById(1);
//        Gson gson = new Gson();
//        String str = gson.toJson(roomList);
//        System.out.println("\n\n\n\n\n" + str + "\n\n\n\n\n\n");
//    }
}
