package by.kanarski.booking.commands.impl.admin.databaseCommands.hotels;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.entities.Hotel;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.HotelServiceImpl;
import by.kanarski.booking.utils.RequestParser;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AlterHotelsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
        String page = null;
        HttpSession session = request.getSession();
        List<Hotel> newHotels = new ArrayList<>();
        List<Hotel> changedHotels = new ArrayList<>();
        List<Hotel> hotelList = RequestParser.parseHotelList(request);
        getNewHotels(hotelList, newHotels, changedHotels);

        try {
            HotelServiceImpl.getInstance().addList(newHotels);
            HotelServiceImpl.getInstance().updateList(changedHotels);
            if (RequestParser.isAjaxRequest(request)) {
                servletAction = ServletAction.NO_ACTION;
                writeResponse(response);
            } else {
                servletAction = ServletAction.FORWARD_PAGE;
                request.setAttribute(Parameter.OPERATION_MESSAGE, "done");
                page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
            }
        } catch (ServiceException e) {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ERROR_PAGE_PATH;
            handleServiceException(request, e);
        }
        servletAction.setPage(page);
        return servletAction;
    }

    private void writeResponse(HttpServletResponse response) {
        try {
            String message = "done";
            PrintWriter out = response.getWriter();
            out.write(message);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getNewHotels(List<Hotel> initialHotelList, List<Hotel> newHotels, List<Hotel> changedHotels) {
        for (Hotel hotel : initialHotelList) {
            if (hotel.getId() == -1) {
                newHotels.add(hotel);
            } else {
                changedHotels.add(hotel);
            }
        }
    }

    public static void main(String[] args) {
        String str = null;
        System.out.println(Long.valueOf(""));
    }


}
