package by.kanarski.booking.commands.impl.admin.database.room;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.OperationMessageKeys;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Value;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class AlterRoomsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);
        String subCommand = request.getParameter(Parameter.SUB_COMMAND);
        try {
            List<RoomDto> roomDtoList = RequestParser.parseRoomDtoList(request);
//            List<Room> roomList = DtoToEntityConverter.toRoomList(roomDtoList, locale, currency);
            switch (subCommand) {
                case Value.ADD_NEW: {
                    RoomServiceImpl.getInstance().addList(roomDtoList);
                    break;
                }
                case Value.CHANGE_EXISTING: {
                    RoomServiceImpl.getInstance().updateList(roomDtoList);
                    break;
                }
            }
            List<RoomDto> newRoomDtoList = RoomServiceImpl.getInstance().getAll();
            session.setAttribute(Parameter.ROOM_LIST, newRoomDtoList);
            session.setAttribute(Parameter.ROOM_DTO_LIST, newRoomDtoList);
            ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
            String responseText = bundle.getString(OperationMessageKeys.DATABASE_CHANGE_SUCCES);
            if (RequestParser.isAjaxRequest(request)) {
                servletAction = ServletAction.AJAX_REQUEST;
                writeResponse(response, responseText);
                page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
            } else {
                servletAction = ServletAction.FORWARD_PAGE;
                request.setAttribute(Parameter.OPERATION_MESSAGE, responseText);
                page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
            }
        } catch (ServiceException e) {
            servletAction = ServletAction.FORWARD_PAGE;
            page = PagePath.ERROR;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
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
