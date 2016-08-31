package by.kanarski.booking.i18n.l10n.filler;


import by.kanarski.booking.commands.factory.CommandFactory;
import by.kanarski.booking.commands.factory.CommandType;

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

    public Filler defineFiller(String pagePath) {
        String pageNameRegExp = "/\\w+\\.";
        Pattern pattern = Pattern.compile(pageNameRegExp);
        Matcher matcher = pattern.matcher(pagePath);
        matcher.find();
        String pageName = matcher.group();
        pageName = pageName.substring(1, pageName.length() - 1);
        FillerManager fillerManager = FillerManager.valueOf(pageName.toUpperCase());
        Filler current = fillerManager.getFiller();
        return current;
    }
}
