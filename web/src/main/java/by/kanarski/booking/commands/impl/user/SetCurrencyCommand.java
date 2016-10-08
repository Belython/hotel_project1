package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.i18n.l10n.filler.Filler;
import by.kanarski.booking.i18n.l10n.filler.factory.FillerFactory;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.RequestParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Currency;
import java.util.Locale;

public class SetCurrencyCommand implements ICommand {

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page;
        HttpSession session = request.getSession();
        Currency currency = RequestParser.parseCurrency(request);
        session.setAttribute(Parameter.CURRENCY, currency);
        page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        Filler filler = FillerFactory.getInstance().defineFiller(page);
        filler.fill(request);
        servletAction.setPage(page);
        if (page == null) {
            page = PagePath.INDEX_PAGE_PATH;
            servletAction.setPage(page);
        }
        return servletAction;
    }
}
