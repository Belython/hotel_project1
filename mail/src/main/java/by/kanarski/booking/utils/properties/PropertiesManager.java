package by.kanarski.booking.utils.properties;

import java.io.*;
import java.util.Properties;

public class PropertiesManager {

    private static PropertiesManager instance;

    private final String OUTGOING_MAIL_PROPERTIES = "outgoingMail.xml";
    
    private PropertiesManager(){
        
    }

    public static synchronized PropertiesManager getInstance() {
        if (instance == null) {
            instance = new PropertiesManager();
        }
        return instance;
    }

    public Properties getOutgoingMailProperties() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(OUTGOING_MAIL_PROPERTIES);
            properties.loadFromXML(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
    
}
