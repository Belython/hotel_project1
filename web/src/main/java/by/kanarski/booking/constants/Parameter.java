package by.kanarski.booking.constants;

public class Parameter {

    //User parameters

    public static final String USER = "user";
    public static final String USER_ID = "clientId";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_EMAIL = "email";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "userStatus";

    //Bill parameters

    public static final String BILL_LIST = "billList";
    public static final String BILL_TO_PAY = "billToPay";

    public static final String ORDER = "order";

    //Orrder parameters

    public static final String ORDER_TOTAL_PERSONS = "totalPersons";
    public static final String ORDER_CHECK_IN_DATE = "checkInDate";
    public static final String ORDER_CHECK_OUT_DATE = "checkOutDate";

    //Location parameters

    public static final String LOCATION_COUNTRY = "country";
    public static final String LOCATION_CITY = "city";

    //Hotel parameters

    public static final String HOTEL = "hotel";
    public static final String HOTEL_ID = "hotelId";
    public static final String HOTEL_COUNTRY = "hotelCountry";
    public static final String HOTEL_CITY = "hotelCity";
    public static final String HOTEL_NAME = "hotelName";
    public static final String HOTEL_STATUS = "hotelStatus";
    public static final String HOTEL_HOTELS_LIST = "hotelsList";
    public static final String HOTEL_SELECTED_HOTEL = "selectedHotel";

    //Locale parameters

    public static final String LOCALE_LIST = "localeList";
    public static final String LOCALE = "locale";

    //General attributes

    public static final String COMMAND = "command";
    public static final String ERROR_DATABASE = "errorDatabase";
    public static final String ERROR_LOGIN_OR_PASSWORD = "errorLoginOrPassword";
    public static final String ERROR_USER_EXISTS = "errorUserExists";
    public static final String OPERATION_MESSAGE = "operationMessage";
    public static final String CURRENT_PAGE_PATH = "currentPagePath";
    public static final String ANY_HOTEL = "any";
    public static final String IS_AJAX_REQUEST = "isAjaxRequest";

    private Parameter() {
    }
}
