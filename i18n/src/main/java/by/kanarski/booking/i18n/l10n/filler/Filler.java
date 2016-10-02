package by.kanarski.booking.i18n.l10n.filler;

import by.kanarski.booking.constants.Parameter;
import by.kanarski.booking.constants.ResourcePath;
import by.kanarski.booking.i18n.l10n.filler.factory.ContentType;

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
                String contentType = ContentType.getContentType(contentName);
                switch (contentType) {
                    case ContentType.STRING: {
                        fillString(keyList, bundle, request);
                        break;
                    }
                    case ContentType.MAP: {
                        fillMap(contentName, keyList, bundle, request);
                    }
                }
            }
        }
    }

    private void fillString(List<String> keyList, ResourceBundle bundle, HttpServletRequest request) {
        for (String key : keyList) {
            String attributeName = key.replace(".", "_");
            String text = bundle.getString(key);
            request.setAttribute(attributeName, text);
        }
    }

    private void fillMap(String contentName, List<String> keyList, ResourceBundle bundle, HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        for (String key : keyList) {
            String reg = "\\w+\\.";
            Pattern pattern = Pattern.compile(reg);
            Matcher matcher = pattern.matcher(key);
            matcher.find();
            String attributeName = matcher.replaceFirst("");
            String text = bundle.getString(key);
            map.put(attributeName, text);
        }
        request.setAttribute(contentName, map);
    }
}
