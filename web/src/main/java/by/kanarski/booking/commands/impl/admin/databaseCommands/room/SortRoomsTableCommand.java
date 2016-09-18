package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

public class SortRoomsTableCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        HttpSession session = request.getSession();
        String sortingOption = request.getParameter(Parameter.SORTING_OPTION);
        String sortingDirection = request.getParameter(Parameter.SORTING_DIRECTION);
        List<Hotel> hotelList = (List<Hotel>) session.getAttribute(Parameter.HOTEL_LIST);
        Comparator<Hotel> sortBy = new SortBy(sortingOption, sortingDirection);
        hotelList.sort(sortBy);
        session.setAttribute(Parameter.HOTEL_LIST, hotelList);
        String page = PagePath.HOTEL_LIST_PAGE_PATH;
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

    private void sortHotelsTable(HttpSession session, String sortingOption, String sortingDirection) {
        List<Hotel> hotelList = (List<Hotel>) session.getAttribute(Parameter.HOTEL_LIST);
        Comparator<Hotel> sortBy = new SortBy(sortingOption, sortingDirection);
        hotelList.sort(sortBy);
        session.setAttribute(Parameter.HOTEL_LIST, hotelList);
    }

    private class SortBy implements Comparator<Hotel> {

        boolean ascending;
        String sortingOption;

        SortBy(String sortingOption, String sortingDirection) {
            ascending = sortingDirection.equals(Value.ASCENDING);
            this.sortingOption = sortingOption;
        }

        @Override
        public int compare(Hotel hotel1, Hotel hotel2) {
            int result;
            String hotel1Parameter;
            String hotel2Parameter;
            switch (sortingOption) {
                case Value.HOTEL_NAME: {
                    hotel1Parameter = hotel1.getHotelName();
                    hotel2Parameter = hotel2.getHotelName();
                    break;
                }
                case Value.HOTEL_COUNTRY: {
                    hotel1Parameter = hotel1.getHotelLocation().getCountry();
                    hotel2Parameter = hotel2.getHotelLocation().getCountry();
                    break;
                }
                case Value.HOTEL_CITY: {
                    hotel1Parameter = hotel1.getHotelLocation().getCity();
                    hotel2Parameter = hotel2.getHotelLocation().getCity();
                    break;
                }
                default: {
                    hotel1Parameter = hotel1.getHotelName();
                    hotel2Parameter = hotel2.getHotelName();
                }
            }

            int rez = hotel1Parameter.compareTo(hotel2Parameter);
            if (rez < 0) {
                if (ascending) {
                    result = -1;
                } else {
                    result = 0;
                }
            } else {
                if (ascending) {
                    result = 0;
                } else {
                    result = -1;
                }
            }
            return result;
        }
    }
}





