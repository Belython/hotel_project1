package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.BillServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class MakeBillCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        try {
            Bill bill = RequestParser.parseBill(request);
            long checkInDate = bill.getCheckInDate();
            long checkOutDate = bill.getCheckOutDate();
            List<Long> requestedRoomIdList = bill.getBookedRoomIdList();
            List<Room> requestedRoomList = RoomServiceImpl.getInstance().getByIdList(requestedRoomIdList);
            List<Room> bookedRoomList = new ArrayList<>();
            for (Room room : requestedRoomList) {
                TreeMap<Long, Long> bookedDates = room.getBookedDates();
                if (bookedDates == null) {
                    bookedDates = new TreeMap<>();
                }
                bookedDates.put(checkInDate, checkOutDate);
                room.setBookedDates(bookedDates);
                bookedRoomList.add(room);
            }
            RoomServiceImpl.getInstance().reserveRoomList(bookedRoomList);
            BillServiceImpl.getInstance().add(bill);
            page = PagePath.INDEX_PAGE_PATH;
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
