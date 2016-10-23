package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.commands.factory.CommandType;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.GlobalHotelDto;
import by.kanarski.booking.dto.HotelDto;
import by.kanarski.booking.dto.OrderDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.GlobalHotelServiceImpl;
import by.kanarski.booking.services.impl.HotelServiceImpl;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class GoToSelectHotelCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction;
        String page = null;
        HttpSession session = request.getSession();
        try {
            OrderDto orderDto = RequestParser.parseOrderDto(request);
            String hotelName = orderDto.getHotel().getHotelName();
            if (!hotelName.equals(Value.HOTEL_ALL_HOTELS)) {
                HotelDto hotelDto = HotelServiceImpl.getInstance().getByHotelName(hotelName);
                orderDto.setHotel(hotelDto);
                servletAction = ServletAction.CALL_COMMAND;
                servletAction.setCommandName(CommandType.GOTOSELECTROOMS.name());
            } else {
                List<GlobalHotelDto> globalHotelDtoList = GlobalHotelServiceImpl.getInstance().getByOrder(orderDto);
                session.setAttribute(Parameter.SELECTED_GLOBAL_HOTEL_LIST, globalHotelDtoList);
                page = PagePath.SELECT_HOTEL;
                servletAction = ServletAction.FORWARD_PAGE;
            }
            session.setAttribute(Parameter.ORDER, orderDto);
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            servletAction = ServletAction.REDIRECT_PAGE;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

}
