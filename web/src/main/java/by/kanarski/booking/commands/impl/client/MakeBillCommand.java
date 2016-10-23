package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.exceptions.LocalisationException;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.BillServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.TreeMap;

public class MakeBillCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        try {
            BillDto billDto = RequestParser.parseBillDto(request);
            String checkInDate = billDto.getCheckInDate();
            String checkOutDate = billDto.getCheckOutDate();
            List<RoomDto> requestedRoomList = billDto.getBookedRoomList();
            for (RoomDto roomDto : requestedRoomList) {
                TreeMap<String, String> bookedDates = roomDto.getBookedDates();
                if (bookedDates == null) {
                    bookedDates = new TreeMap<>();
                }
                bookedDates.put(checkInDate, checkOutDate);
                roomDto.setBookedDates(bookedDates);
            }
            RoomServiceImpl.getInstance().reserveRoomList(requestedRoomList);
            BillServiceImpl.getInstance().add(billDto);
            page = PagePath.INDEX_PAGE_PATH;
        } catch (ServiceException | LocalisationException e) {
            page = PagePath.ERROR;
            handleServiceException(request, e);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
