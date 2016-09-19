package by.kanarski.booking.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dkanarsky on 29.08.2016.
 */
public class Value {

    public static final List<String> LOCALE_LIST = Arrays.asList("ru_RU", "en_US", "en_EN");

    public static final String ASCENDING = "ascending";
    public static final String DESCENDING = "descending";

    public static final String HOTEL_ALL_HOTELS = "allHotels";
    public static final String HOTEL_CITY = "hotelCity";
    public static final String HOTEL_COUNTRY = "hotelCountry";
    public static final String HOTEL_NAME = "hotelName";

    public static final String ROOM_TYPE_NAME = "roomTypeName";

    public static final List<String> STATUS_LIST = Arrays.asList("active", "deleted");

    //Sub commands constants

    public static final String ADD_NEW = "addNew";
    public static final String CHANGE_EXISTING = "changeExisting";

    //Database redactor types

    public static final String ROOM_REDACTOR = "roomRedactor";

}
