package by.kanarski.booking.managers;

import by.kanarski.booking.constants.ResourcePath;

import java.util.ResourceBundle;


public class MessageManager {
    private static MessageManager instance;
    private final ResourceBundle bundle = ResourceBundle.getBundle(ResourcePath.MESSAGES_SOURCE);

    private MessageManager() {
    }

    public static synchronized MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return bundle.getString(key);
    }
}
