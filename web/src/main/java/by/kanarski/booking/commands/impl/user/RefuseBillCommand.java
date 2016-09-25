package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.*;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.MessageManager;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.BillServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.utils.DtoToEntityConverter;

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
        try {
            Bill refusedBill = refuseBill(request);
            List<BillDto> newBillDtoList = getNewBillDtoList(session, refusedBill);
            session.setAttribute(Parameter.BILL_DTO_LIST, newBillDtoList);
            request.setAttribute(Parameter.OPERATION_MESSAGE, MessageManager.getInstance().getProperty(MessageConstants.SUCCESS_OPERATION));
            page = PagePath.ACCOUNT_PAGE_PATH;
        } catch (ServiceException e) {
            page = PagePath.ERROR_PAGE_PATH;
            handleServiceException(request, e);
        }
        session.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        request.setAttribute(Parameter.CURRENT_PAGE_PATH, page);
        servletAction.setPage(page);
        return servletAction;
    }

    private Bill refuseBill(HttpServletRequest request) throws ServiceException{
        long refusedBillId = Long.valueOf(request.getParameter(Parameter.BILL_TO_REFUSE));
        Bill refusedBill = BillServiceImpl.getInstance().getById(refusedBillId);
        long checkInDate = refusedBill.getCheckInDate();
        List<Long> roomIdList = refusedBill.getBookedRoomIdList();
        List<Room> roomList = RoomServiceImpl.getInstance().getByIdList(roomIdList);
        List<Room> refusedRoomList = new ArrayList<>();
        for (Room room : roomList) {
            TreeMap<Long, Long> bookedDates = room.getBookedDates();
            if (bookedDates != null) {
                bookedDates.remove(checkInDate);
                //Да, знаю что лишнее
                room.setBookedDates(bookedDates);
            }
            refusedRoomList.add(room);
        }
        refusedBill.setBillStatus(FieldValue.STATUS_REFUSED);
        RoomServiceImpl.getInstance().updateList(refusedRoomList);
        BillServiceImpl.getInstance().update(refusedBill);
        return refusedBill;
    }

    private List<BillDto> getNewBillDtoList(HttpSession session, Bill refusedBill) {
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        long refusedBillId = refusedBill.getBillId();
        BillDto refusedBillDto = DtoToEntityConverter.convertToBillDto(refusedBill, locale);
        List<BillDto> billDtoList = (List<BillDto>) session.getAttribute(Parameter.BILL_DTO_LIST);
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
