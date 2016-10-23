package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.OperationMessageKeys;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.BillServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

public class RefuseBillCommand extends AbstractCommand{

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
        try {
            BillDto refusedBillDto = refuseBill(request);
            List<BillDto> newBillDtoList = getNewBillDtoList(session, refusedBillDto);
            session.setAttribute(Parameter.BILL_LIST, newBillDtoList);
            request.setAttribute(Parameter.OPERATION_MESSAGE, bundle.getString(OperationMessageKeys.SUCCESS_OPERATION));
            page = PagePath.ACCOUNT_PAGE_PATH;
        } catch (ServiceException e) {
            page = PagePath.ERROR;
            handleServiceException(request);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

    private BillDto refuseBill(HttpServletRequest request) throws ServiceException{
        long refusedBillId = Long.valueOf(request.getParameter(Parameter.BILL_TO_REFUSE));
        BillDto refusedBillDto = BillServiceImpl.getInstance().getById(refusedBillId);
        String checkInDate = refusedBillDto.getCheckInDate();
//        List<Long> roomIdList = refusedBillDto.getBookedRoomIdList();
//        List<Room> roomList = RoomServiceImpl.getInstance().getByIdList(roomIdList);
        List<RoomDto> refusedRoomDtoList = refusedBillDto.getBookedRoomList();
        for (RoomDto roomDto : refusedRoomDtoList) {
            TreeMap<String, String> bookedDates = roomDto.getBookedDates();
            if (bookedDates != null) {
                bookedDates.remove(checkInDate);
                //Да, знаю что лишнее
                roomDto.setBookedDates(bookedDates);
            }
        }
        refusedBillDto.setBillStatus(FieldValue.STATUS_REFUSED);
        RoomServiceImpl.getInstance().updateList(refusedRoomDtoList);
        BillServiceImpl.getInstance().update(refusedBillDto);
        return refusedBillDto;
    }

    private List<BillDto> getNewBillDtoList(HttpSession session, BillDto refusedBillDto) {
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);
        long refusedBillId = refusedBillDto.getBillId();
        List<BillDto> billDtoList = (List<BillDto>) session.getAttribute(Parameter.BILL_LIST);
        for (BillDto billDto : billDtoList) {
            long billId = billDto.getBillId();
            if (billId == refusedBillId) {
                billDtoList.remove(billDto);
                billDtoList.add(refusedBillDto);
                break;
            }
        }
        return billDtoList;
    }
}
