package by.kanarski.booking.i18n.l10n.filler.factory;

import by.kanarski.booking.constants.ContentName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dkanarsky on 26.09.2016.
 */
public class ContentType {

    public static final String TEXT = "text";
    public static final String MAP = "map";
    public static final String LIST = "list";

    private static final List<String> MAP_CONTENT = Arrays.asList(
            ContentName.LOCALE_MAP
    );
    private static final List<String> LIST_CONTENT = Arrays.asList();


    private ContentType() {

    }

    public static String getContentType(String contentName) {
        if (contentName.equals(TEXT)) {
            return TEXT;
        }
        ;
        if (MAP_CONTENT.contains(contentName)) {
            return MAP;
        }
        ;
        if (LIST_CONTENT.contains(contentName)) {
            return LIST;
        };
        return TEXT;
    }

}
