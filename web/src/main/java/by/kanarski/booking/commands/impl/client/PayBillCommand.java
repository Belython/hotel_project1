package by.kanarski.booking.commands.impl.client;

import by.kanarski.booking.commands.AbstractCommand;
import by.kanarski.booking.constants.FieldValue;
import by.kanarski.booking.constants.OperationMessageKeys;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.dto.BillDto;
import by.kanarski.booking.dto.UserDto;
import by.kanarski.booking.exceptions.ServiceException;
import by.kanarski.booking.managers.ResourceBuilder;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.services.impl.BillServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class PayBillCommand extends AbstractCommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page = null;
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        Currency currency = (Currency) session.getAttribute(Parameter.CURRENCY);
        try {
            UserDto user = (UserDto) session.getAttribute(Parameter.USER);
            long billId = Long.valueOf(request.getParameter(Parameter.BILL_TO_PAY));
            BillDto billToPay = BillServiceImpl.getInstance().getById(billId);
            billToPay.setBillStatus(FieldValue.STATUS_PAID);
            BillServiceImpl.getInstance().update(billToPay);
            List<BillDto> billDtoList = BillServiceImpl.getInstance().getByUserId(user.getUserId());
            session.setAttribute(Parameter.BILL_LIST, billDtoList);
            ResourceBundle bundle = ResourceBuilder.OPERATION_MESSAGES.setLocale(locale).create();
            String errorMessage = bundle.getString(OperationMessageKeys.ERROR_DATABASE);
            request.setAttribute(Parameter.ERROR_DATABASE, errorMessage);
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
}
