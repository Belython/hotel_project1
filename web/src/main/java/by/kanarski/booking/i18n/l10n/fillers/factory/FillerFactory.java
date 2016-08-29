package by.kanarski.booking.i18n.l10n.fillers.factory;


import by.kanarski.booking.i18n.l10n.fillers.IFiller;

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

    public IFiller defineFiller(String page) {
        IFiller current = null;
        FillerType type = FillerType.valueOf(page);
        current = type.getCurrentFiller();
        return current;
    }
}
