package by.kanarski.booking.i18n.l10n.filler.factory;

import by.kanarski.booking.constants.ContentName;
import by.kanarski.booking.i18n.l10n.filler.Filler;
import by.kanarski.booking.constants.PageTextContentKeys;

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
    ),
    REMINDPASSWORD(
        Arrays.asList(getRemindPassword(), getHeader())
    );

    private List<Map<String, List<String>>> pageDescriptor;

    FillerManager(List<Map<String, List<String>>> pageDescriptor) {
        this.pageDescriptor = pageDescriptor;
    }


    public Filler getFiller() {
        return new Filler(pageDescriptor);
    }

    public static Map<String, List<String>> getIndex() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.INDEX);
        return contentMap;
    }

    public static Map<String, List<String>> getHeader() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.HEADER);
        contentMap.put(ContentName.LOCALE_MAP, PageTextContentKeys.LOCALE_LIST);
        return contentMap;
    }

    public static Map<String, List<String>> getError() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.ERROR);
        return contentMap;
    }

    public static Map<String, List<String>> getRegistration() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.REGISTRATION);
        return contentMap;
    }

    public static Map<String, List<String>> getAccount() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.ACCOUNT);
        return contentMap;
    }

    public static Map<String, List<String>> getSelectHotel() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.SELECT_HOTEL);
        return contentMap;
    }

    public static Map<String, List<String>> getSelectRoom() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.SELECT_ROOM);
        return contentMap;
    }

    public static Map<String, List<String>> getAminMain() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.ADMIN_MAIN);
        return contentMap;
    }

    public static Map<String, List<String>> getSideBar() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.SIDE_BAR);
        return contentMap;
    }

    public static Map<String, List<String>> getRoomsRedactor() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.ROOMS_REDACTOR);
        return contentMap;
    }

     public static Map<String, List<String>> getRemindPassword() {
        Map<String, List<String>> contentMap = new HashMap<>();
        contentMap.put(ContentName.STRING, PageTextContentKeys.REMIND_PASSWORD);
        return contentMap;
    }

}
