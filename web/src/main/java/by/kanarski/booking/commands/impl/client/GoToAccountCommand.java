package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.entities.Bill;
import by.kanarski.booking.entities.Room;
import by.kanarski.booking.entities.User;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.BillServiceImpl;
import by.kanarski.booking.services.impl.RoomServiceImpl;
import by.kanarski.booking.utils.DtoToEntityConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.TreeMap;

public class GoToAccountCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale =(Locale) session.getAttribute(Parameter.LOCALE);
        try {

            User user = (User) session.getAttribute(Parameter.USER);
            List<Bill> billList = BillServiceImpl.getInstance().getByUserId(user.getUserId());
            List<BillDto> billDtoList = DtoToEntityConverter.convertToBillDtoList(billList, locale);
            session.setAttribute(Parameter.BILL_DTO_LIST, billDtoList);
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

    public static void main(String[] args) throws ServiceException{
        List<Room> rl = RoomServiceImpl.getInstance().getByHotelId(1);
        for (Room room : rl) {
            TreeMap<Long, Long> dd = room.getBookedDates();
            if (dd != null) {
                NavigableSet<Long> nks = dd.navigableKeySet();
                for (Long k : nks) {
                    Long k1 = dd.get(k);
                    System.out.println("Start " + k + " end " + k1);
                }
            }
        }

        System.out.println("lool");
    }
}
