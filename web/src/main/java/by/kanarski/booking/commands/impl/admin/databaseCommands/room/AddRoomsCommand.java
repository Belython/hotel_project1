package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.MessageConstants;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.utils.DtoToEntityConverter;
import by.kanarski.booking.utils.Message;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

public class AddRoomsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        List<RoomDto> roomDtoList = RequestParser.parseRoomDtoList(request);
        List<Room> roomList = DtoToEntityConverter.covertToRoomList(roomDtoList, locale);
        try {
            RoomServiceImpl.getInstance().updateList(roomList);
            servletAction = ServletAction.FORWARD_PAGE;
            request.setAttribute(Parameter.OPERATION_MESSAGE,
                    Message.getProperty(MessageConstants.DATABASE_CHANGE_SUCCES, locale));
            page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        } catch (ServiceException e) {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ERROR_PAGE_PATH;
            handleServiceException(request, e);
        }
        servletAction.setPage(page);
        return servletAction;
    }
}
