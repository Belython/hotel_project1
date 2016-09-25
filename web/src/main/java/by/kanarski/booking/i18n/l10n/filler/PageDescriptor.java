package by.kanarski.booking.i18n.l10n.filler;

import java.util.Arrays;
import java.util.List;

public class PageDescriptor {

    public static final List<List<String>> INDEX = Arrays.asList(
            PageTextContentName.INDEX,
            PageTextContentName.HEADER
    );

    public static final List<List<String>> ACCOUNT = Arrays.asList(
            PageTextContentName.ACCOUNT,
            PageTextContentName.HEADER
    );

    public static final List<List<String>> SELECT_HOTEL = Arrays.asList(
            PageTextContentName.SELECT_HOTEL,
            PageTextContentName.HEADER
    );

    public static final List<List<String>> SELECT_ROOM = Arrays.asList(
            PageTextContentName.SELECT_ROOM,
            PageTextContentName.HEADER
    );

    public static final List<List<String>> REGISTRATION = Arrays.asList(
            PageTextContentName.REGISTRATION,
            PageTextContentName.HEADER
    );

    public static final List<List<String>> ERROR = Arrays.asList(
            PageTextContentName.ERROR,
            PageTextContentName.HEADER
    );

    public static final List<List<String>> ADMIN_MAIN = Arrays.asList(
            PageTextContentName.ADMIN_MAIN,
            PageTextContentName.SIDE_BAR,
            PageTextContentName.HEADER
    );

    public static final List<List<String>> ADMIN_ROOMS_REDACTOR = Arrays.asList(
            PageTextContentName.ROOMS_REDACTOR,
            PageTextContentName.SIDE_BAR,
            PageTextContentName.HEADER
    );

    private PageDescriptor() {

    }



}
