package by.kanarski.booking.i18n.l10n.filler;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.ResourcePath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Filler {

    private List<Map<String, List<String>>> pageDescriptor;

    public Filler(List<Map<String, List<String>>> pageDescriptor) {
        this.pageDescriptor = pageDescriptor;
    }

    public void fill (HttpServletRequest request) {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(Parameter.LOCALE);
        ResourceBundle bundle = ResourceBundle.getBundle(ResourcePath.TEXT_SOURCE, locale);
        for (Map<String, List<String>> contentMap : pageDescriptor) {
            Set<String> contentNames = contentMap.keySet();
            for (String contentName : contentNames) {
                List<String> keyList = contentMap.get(contentName);
                switch (contentName) {
                    case ContentName.TEXT: {
                        for (String key : keyList) {
                            String attributeName = key.replace(".", "_");
                            String text = bundle.getString(key);
                            request.setAttribute(attributeName, text);
                        }
                        break;
                    }
                    case ContentName.LOCALE_MAP: {
                        Map<String, String> localeMap = new LinkedHashMap<>();
                        for (String key : keyList) {
                            String reg = "\\w+\\.";
                            Pattern pattern = Pattern.compile(reg);
                            Matcher matcher = pattern.matcher(key);
                            matcher.find();
                            String attributeName = matcher.replaceFirst("");
                            String text = bundle.getString(key);
                            localeMap.put(attributeName, text);
                        }
                        request.setAttribute(contentName, localeMap);
                    }
                }
            }
        }
    }
}
