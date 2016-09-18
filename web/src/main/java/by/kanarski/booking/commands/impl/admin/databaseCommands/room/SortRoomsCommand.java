package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.AlphanumComparator;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.SortResultCounter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class SortRoomsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        HttpSession session = request.getSession();
        String sortingOption = request.getParameter(Parameter.SORTING_OPTION);
        String sortingDirection = request.getParameter(Parameter.SORTING_DIRECTION);
        List<Room> roomList = (List<Room>) session.getAttribute(Parameter.ROOM_LIST);
        Comparator<Room> sortBy = new SortBy(sortingOption, sortingDirection);
        roomList.sort(sortBy);
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        List<RoomDto> roomDtoList = DtoToEntityConverter.covertToRoomDtoList(roomList, locale);
        session.setAttribute(Parameter.ROOM_DTO_LIST, roomDtoList);
        String page = PagePath.ROOM_LIST_PAGE_PATH;
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

    private class SortBy implements Comparator<Room> {

        boolean ascending;
        String sortingOption;

        SortBy(String sortingOption, String sortingDirection) {
            ascending = sortingDirection.equals(Value.ASCENDING);
            this.sortingOption = sortingOption;
        }

        @Override
        public int compare(Room room1, Room room2) {
            int result;
            Object r1Parameter;
            Object r2Parameter;
            AlphanumComparator alphanumComparator = new AlphanumComparator();
            switch (sortingOption) {
                case Parameter.HOTEL_NAME: {
                    r1Parameter = room1.getRoomHotel().getHotelName();
                    r2Parameter = room2.getRoomHotel().getHotelName();
                    break;
                }
                case Parameter.HOTEL_COUNTRY: {
                    r1Parameter = room1.getRoomHotel().getHotelLocation().getCountry();
                    r2Parameter = room2.getRoomHotel().getHotelLocation().getCountry();
                    break;
                }
                case Parameter.HOTEL_CITY: {
                    r1Parameter = room1.getRoomHotel().getHotelLocation().getCity();
                    r2Parameter = room2.getRoomHotel().getHotelLocation().getCity();
                    break;
                }
                case Parameter.ROOM_TYPE_NAME: {
                    r1Parameter = room1.getRoomType().getRoomTypeName();
                    r2Parameter = room2.getRoomType().getRoomTypeName();
                    break;
                }
                case Parameter.ROOM_NUMBER: {
                    r1Parameter = room1.getRoomNumber();
                    r2Parameter = room2.getRoomNumber();
                    break;
                }
                case Parameter.ROOM_BOOKING_START_DATE: {
                    r1Parameter = room1.getBookingStartDate();
                    r2Parameter = room2.getBookingStartDate();
                    break;
                }
                case Parameter.ROOM_BOOKING_END_DATE: {
                    r1Parameter = room1.getBookingEndDate();
                    r2Parameter = room2.getBookingEndDate();
                    break;
                }
                default: {
                    r1Parameter = room1.getRoomHotel().getHotelName();
                    r2Parameter = room2.getRoomHotel().getHotelName();
                }
            }
            String r1ParamAsString = r1Parameter.toString();
            String r2ParamAsString = r2Parameter.toString();
            int res = alphanumComparator.compare(r1ParamAsString, r2ParamAsString);
            result = SortResultCounter.getResult(res, ascending);
            return result;
        }

    }
}





