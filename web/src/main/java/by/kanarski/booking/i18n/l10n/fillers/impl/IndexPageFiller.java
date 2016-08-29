package by.kanarski.booking.i18n.l10n.fillers.impl;

import by.kanarski.booking.constants.PageTextContentName;
import by.kanarski.booking.constants.ResourcePath;
import by.kanarski.booking.constants.Attribute;
import by.kanarski.booking.i18n.l10n.fillers.IFiller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class IndexPageFiller implements IFiller {

    @Override
    public void execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Attribute.LOCALE);
        ResourceBundle bundle = ResourceBundle.getBundle(ResourcePath.TEXT_SOURCE, locale);
        for (String element : PageTextContentName.INDEX_PAGE_TEXT_CONTENT) {
            request.setAttribute(element, bundle.getString(element));
        }
    }
}
