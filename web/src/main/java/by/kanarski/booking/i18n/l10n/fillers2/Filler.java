package by.kanarski.booking.i18n.l10n.fillers2;

import by.kanarski.booking.constants.Attribute;
import by.kanarski.booking.constants.PageTextContentName;
import by.kanarski.booking.constants.ResourcePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by dkanarsky on 31.08.2016.
 */
public class Filler {

    public void fill (HttpServletRequest request, List<List<String>> pageDescriptor) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Attribute.LOCALE);
        ResourceBundle bundle = ResourceBundle.getBundle(ResourcePath.TEXT_SOURCE, locale);
        String
        Pattern pattern
        for (List<String> contentList : pageDescriptor) {
            for (String contentName : contentList) {
                request.setAttribute(contentName, bundle.getString(contentName));
            }
        }
    }

}
