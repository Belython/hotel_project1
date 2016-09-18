package by.kanarski.booking.utils;

import by.kanarski.booking.constants.ResourcePath;

import java.util.Locale;
import java.util.ResourceBundle;


public class Message {

    private Message() {
    }

    public static String getProperty(String key, Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle(ResourcePath.MESSAGES_SOURCE, locale);
        return bundle.getString(key);
    }

}
