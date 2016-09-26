package by.kanarski.booking.i18n.l10n.filler.factory;

import by.kanarski.booking.constants.ContentName;
import by.kanarski.booking.i18n.l10n.filler.Filler;
import by.kanarski.booking.constants.PageTextContentName;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FillerManager {
    INDEX (
        Arrays.asList(getIndex(), getHeader())
    ),
    ERROR (
        Arrays.asList(getError(), getHeader())
    ),
    REGISTRATION (
        Arrays.asList(getRegistration(), getHeader())
    ),
    SELECTHOTEL (
        Arrays.asList(getSelectHotel(), getHeader())
    ),
    SELECTROOM (
        Arrays.asList(getSelectRoom(), getHeader())
    ),
    ACCOUNT(
        Arrays.asList(getAccount(), getHeader())
    ),
    ADMINMAIN(
        Arrays.asList(getAminMain(), getSideBar(), getHeader())
    ),
    ROOMSREDACTOR(
        Arrays.asList(getRoomsRedactor(), getSideBar(), getHeader())
    );

    private List<Map<String, List<String>>> pageDescriptor;

    FillerManager(List<Map<String, List<String>>> pageDescriptor) {
        this.pageDescriptor = pageDescriptor;
    }


    public Filler getFiller() {
        return new Filler(pageDescriptor);
    }

    public static Map<String, List<String>> getIndex() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.INDEX);
        return header;
    }

    public static Map<String, List<String>> getHeader() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.HEADER);
        header.put(ContentName.LOCALE_MAP, PageTextContentName.LOCALE_LIST);
        return header;
    }

    public static Map<String, List<String>> getError() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.ERROR);
        return header;
    }

    public static Map<String, List<String>> getRegistration() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.REGISTRATION);
        return header;
    }

    public static Map<String, List<String>> getAccount() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.ACCOUNT);
        return header;
    }

    public static Map<String, List<String>> getSelectHotel() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.SELECT_HOTEL);
        return header;
    }

    public static Map<String, List<String>> getSelectRoom() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.SELECT_ROOM);
        return header;
    }

    public static Map<String, List<String>> getAminMain() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.ADMIN_MAIN);
        return header;
    }

    public static Map<String, List<String>> getSideBar() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.SIDE_BAR);
        return header;
    }

    public static Map<String, List<String>> getRoomsRedactor() {
        Map<String, List<String>> header = new HashMap<>();
        header.put(ContentName.TEXT, PageTextContentName.ROOMS_REDACTOR);
        return header;
    }

}
