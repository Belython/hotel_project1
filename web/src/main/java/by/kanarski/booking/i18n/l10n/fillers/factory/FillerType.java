package by.kanarski.booking.i18n.l10n.fillers.factory;

import by.kanarski.booking.i18n.l10n.fillers.IFiller;
import by.kanarski.booking.i18n.l10n.fillers.impl.IndexPageFiller;

public enum FillerType {
    INDEX;

    public IFiller getCurrentFiller() throws EnumConstantNotPresentException {
        switch (this) {
            case INDEX: {
                return new IndexPageFiller();
            }
            default: {
                return null;
            }
        }
    }

}
