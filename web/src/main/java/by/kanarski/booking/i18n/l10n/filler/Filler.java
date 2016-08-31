package by.kanarski.booking.i18n.l10n.filler;

import by.kanarski.booking.constants.Attribute;
import by.kanarski.booking.constants.PageTextContentName;
import by.kanarski.booking.constants.ResourcePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Filler {

    private List<List<String>> pageDescriptor;

    public Filler(List<List<String>> pageDescriptor) {
        this.pageDescriptor = pageDescriptor;
    }

    public void fill (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Attribute.LOCALE);
        ResourceBundle bundle = ResourceBundle.getBundle(ResourcePath.TEXT_SOURCE, locale);
        for (List<String> contentList : pageDescriptor) {
            for (String contentName : contentList) {
                request.setAttribute(contentName, bundle.getString(contentName));
            }
        }
    }




}
