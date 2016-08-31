package by.kanarski.booking.i18n.l10n.fillers.factory;


import by.kanarski.booking.i18n.l10n.fillers.IFiller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FillerFactory {

    private static FillerFactory instance;

    private FillerFactory() {
    }

    public static synchronized FillerFactory getInstance() {
        if (instance == null) {
            instance = new FillerFactory();
        }
        return instance;
    }

    public IFiller defineFiller(String pagePath) {
        String pageNameRegExp = "/\\w+\\.";
        Pattern pattern = Pattern.compile(pageNameRegExp);
        Matcher matcher = pattern.matcher(pagePath);
        matcher.find();
        String pageName = matcher.group();
        pageName = pageName.substring(1, pageName.length() - 1);
        IFiller current = null;
        FillerType type = FillerType.valueOf(pageName.toUpperCase());
        current = type.getCurrentFiller();
        return current;
    }
}
