package by.kanarski.booking.i18n.l10n.filler;

import by.kanarski.booking.constants.PageTextContentName;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dkanarsky on 31.08.2016.
 */
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

    private PageDescriptor() {

    }



}
