package by.kanarski.booking.commands.impl.user;

import by.kanarski.booking.commands.ICommand;
import by.kanarski.booking.constants.PagePath;
import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.Attribute;
import by.kanarski.booking.requestHandler.ServletAction;
import by.kanarski.booking.utils.RequestParameterParser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class SetLocaleCommand implements ICommand {

//    private static void fillPage(String targetPage, HttpServletRequest request) {
//        IFiller filler = FillerFactory.getInstance().defineFiller(targetPage);
//        filler.execute(request);
//    }

    @Override
    public ServletAction execute(HttpServletRequest request, HttpServletResponse response) {
        ServletAction servletAction = ServletAction.FORWARD_PAGE;
        String page;
        HttpSession session = request.getSession();
        //Ищем и задаем локаль пока здесь
        Locale locale = RequestParameterParser.parseLocale(request);
        session.setAttribute(Attribute.LOCALE, locale);
        page = (String) session.getAttribute(Parameter.CURRENT_PAGE_PATH);
        servletAction.setPage(page);
        if (page == null) {
            page = PagePath.INDEX_PAGE_PATH;
            servletAction.setPage(page);
        }
//        fillPage("INDEX", request);
        return servletAction;
    }
}
