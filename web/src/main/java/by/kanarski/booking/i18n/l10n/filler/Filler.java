package by.kanarski.booking.i18n.l10n.filler;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.ResourcePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filler {

    private List<List<String>> pageDescriptor;

    public Filler(List<List<String>> pageDescriptor) {
        this.pageDescriptor = pageDescriptor;
    }

    public void fill (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        ResourceBundle bundle = ResourceBundle.getBundle(ResourcePath.TEXT_SOURCE, locale);
        for (List<String> contentList : pageDescriptor) {
            for (String contentName : contentList) {
                String attributeName = contentName.replace(".", "_");
                request.setAttribute(attributeName, bundle.getString(contentName));
            }
        }
    }

}
