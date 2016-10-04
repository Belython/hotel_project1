package by.kanarski.booking.managers;

import by.kanarski.booking.constants.ResourcePath;

import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceBuilder {

    OPERATION_MESSAGES(ResourcePath.OPERATION_MESSAGES_SOURCE),
    JSP_TEXT(ResourcePath.TEXT_SOURCE),
    EXCEPTION(ResourcePath.EXCEPTION_MESSAGES_SOURCE),
    DATABASE(ResourcePath.DATABASE_SOURCE),

    //Mail resources

    AUTHENTIFICATION(ResourcePath.AUTHENTIFICATION_SOURCE),
    MAIL_MESSAGES(ResourcePath.MAIL_MESSAGES_SOURCE);
    
    private String resoucePath;
    private Locale locale;

    ResourceBuilder(String resoucePath) {
        this.resoucePath = resoucePath;
    }

    public ResourceBuilder setLocale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public ResourceBundle create() {
        ResourceBundle bundle;
        if (locale == null) {
            bundle = ResourceBundle.getBundle(resoucePath);
        } else {
            bundle = ResourceBundle.getBundle(resoucePath, locale);
        }
        return bundle;
    }
    
}
