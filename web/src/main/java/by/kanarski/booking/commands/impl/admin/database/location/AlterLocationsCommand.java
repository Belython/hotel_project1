package by.kanarski.booking.commands.impl.admin.database.location;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.requestHandler.ServletAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AlterLocationsCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = null;
//        String page = null;
//        HttpSession session = request.getSession();
//        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
//        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);
//        String subCommand = request.getParameter(Parameter.SUB_COMMAND);
//        try {
//            List<RoomTypeDto> roomTypeDtoList = RequestParser.parseRoomTypeDtoList(request);
//            for (int i = 0; i < roomTypeDtoList.size(); i++) {
//                RoomTypeDto roomTypeDto = roomTypeDtoList.get(i);
//                if (roomTypeDto == null || roomTypeDto.getRoomTypeName() == null) {
//                    roomTypeDtoList.remove(i);
//                }
//            }
//            List<RoomType> roomTypeList = DtoToEntityConverter.convertToRoomTypeList(roomTypeDtoList, currency);
//            switch (subCommand) {
//                case Value.ADD_NEW: {
//                    RoomTypeServiceImpl.getInstance().addList(roomTypeList);
//                    break;
//                }
//                case Value.CHANGE_EXISTING: {
//                    RoomTypeServiceImpl.getInstance().updateList(roomTypeList);
//                    break;
//                }
//            }
//            List<RoomType> newRoomTypeList = RoomTypeServiceImpl.getInstance().getAll();
//            List<RoomTypeDto> newRoomTypeDtoList = DtoToEntityConverter.toRoomTypeDtoList(roomTypeList, currency);
//            session.setAttribute(Parameter.ROOM_TYPE_LIST, newRoomTypeList);
//            session.setAttribute(Parameter.ROOM_TYPE_DTO_LIST, newRoomTypeDtoList);
//            ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
//            String responseText = bundle.getString(OperationMessageKeys.DATABASE_CHANGE_SUCCES);
//            if (RequestParser.isAjaxRequest(request)) {
//                servletAction = ServletAction.AJAX_REQUEST;
//                writeResponse(response, responseText);
//                page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
//            } else {
//                servletAction = ServletAction.FORWARD_PAGE;
//                request.setAttribute(Parameter.OPERATION_MESSAGE, responseText);
//                page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
//            }
//        } catch (ServiceException e) {
//            servletAction = ServletAction.FORWARD_PAGE;
//            page = PagePath.ERROR;
//            handleServiceException(request);
//        }
//        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
//        servletAction.setPage(page);
        return servletAction;
    }

    private void writeResponse(HttpServletResponse response, String responseText) {
//        try {
//            String contentType = "text/html; charset=UTF-8";
//            response.setContentType(contentType);
//            PrintWriter out = response.getWriter();
//            out.write(responseText);
//            out.flush();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
