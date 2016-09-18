package by.kanarski.booking.commands.impl.admin.databaseCommands.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.MessageConstants;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AlterRoomsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        List<RoomDto> roomDtoList = RequestParser.parseRoomDtoList(request);
        List<Room> roomList = DtoToEntityConverter.covertToRoomList(roomDtoList, locale);
        String subCommand = request.getParameter(Parameter.SUB_COMMAND);
        try {
            switch (subCommand) {
                case Value.ADD_NEW: {
                    RoomServiceImpl.getInstance().addList(roomList);
                    break;
                }
                case Value.CHANGE_EXISTING: {
                    RoomServiceImpl.getInstance().updateList(roomList);
                    break;
                }
            }
            String responseText = Message.getProperty(MessageConstants.DATABASE_CHANGE_SUCCES, locale);
            if (RequestParser.isAjaxRequest(request)) {
                servletAction = ServletAction.NO_ACTION;
                writeResponse(response, responseText);
            } else {
                servletAction = ServletAction.FORWARD_PAGE;
                request.setAttribute(Parameter.OPERATION_MESSAGE, responseText);
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

    private void writeResponse(HttpServletResponse response, String responseText) {
        try {
            String contentType = "text/html; charset=UTF-8";
            response.setContentType(contentType);
            PrintWriter out = response.getWriter();
            out.write(responseText);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
