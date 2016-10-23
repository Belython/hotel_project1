package by.kanarski.booking.constants;

public class Parameter {

    //User parameters

    public static final String USER = "user";
    public static final String USER_ID = "userId";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_LAST_NAME = "lastName";
    public static final String USER_EMAIL = "email";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "userStatus";
    public static final String USER_DTO = "userDto";

    //Bill parameters

    public static final String BILL_LIST = "billList";
    public static final String BILL_TO_PAY = "billToPay";
//    public static final String BILL_DTO_LIST = "billDtoList";
    public static final String BILL_TO_REFUSE = "billToRefuse";

    //Orrder parameters

    public static final String ORDER_TOTAL_PERSONS = "totalPersons";
    public static final String ORDER_CHECK_IN_DATE = "checkInDate";
    public static final String ORDER_CHECK_OUT_DATE = "checkOutDate";
    public static final String ORDER = "order";

    //Location parameters

    public static final String LOCATION_COUNTRY = "country";
    public static final String LOCATION_CITY = "city";
    public static final String LOCATION_COUNTRY_SET = "countrySet";
    public static final String LOCATION_CITY_SET = "citySet";
    public static final String SUPPORTED_COUNTRIES = "supportedCountries";
    public static final String SUPPORTED_CITIES = "supportedCities";

    //Hotel parameters

    public static final String HOTEL = "hotel";
    public static final String HOTEL_ID = "hotelId";
    public static final String HOTEL_LOCATION = "hotelLocatin";
    public static final String HOTEL_COUNTRY = "hotelCountry";
    public static final String HOTEL_CITY = "hotelCity";
    public static final String HOTEL_NAME = "hotelName";
    public static final String HOTEL_STATUS = "hotelStatus";
    public static final String HOTEL_LIST = "hotelList";
    public static final String HOTEL_SET = "hotelSet";
    public static final String HOTEL_NAME_SET = "hotelNameSet";
    public static final String SELECTED_HOTEL = "selectedHotelDto";
    public static final String SUPPORTED_HOTELS = "supportedHotels";
    public static final String HOTEL_DTO_LIST = "hotelDtoList";
    public static final String HOTEL_ID_SET = "hotelIdSet";
    public static final String SELECTED_GLOBAL_HOTEL = "selectedGlobalHotel";
    public static final String SELECTED_GLOBAL_HOTEL_LIST = "selectedGlobalHotelList";


    //Locale parameters

    public static final String LOCALE_LIST = "localeList";
    public static final String LOCALE = "locale";
    public static final String CURRENCY = "currency";

    //RoomType parameters

    public static final String ROOM_TYPE = "roomType";
    public static final String ROOM_TYPE_ID = "roomTypeId";
    public static final String ROOM_TYPE_NAME = "roomTypeName";
    public static final String ROOM_TYPE_MAX_PERSONS = "maxPersons";
    public static final String ROOM_TYPE_PRICE_PER_NIGHT = "pricePerNight";
    public static final String ROOM_TYPE_FACILITIES = "facilities";
    public static final String ROOM_TYPE_STATUS = "roomTypeStatus";
    public static final String ROOM_TYPE_LIST = "roomTypeList";
    public static final String ROOM_TYPE_SET = "roomTypeSet";
    public static final String ROOM_TYPE_NAME_SET = "roomTypeNameSet";
    public static final String ROOM_TYPE_MAX_PERSONS_SET = "maxPersonsSet";
    public static final String ROOM_TYPE_PRICE_PER_NIGHT_SET = "pricePerNightSet";
    public static final String ROOM_TYPE_FACILITIES_SET = "facilitiesSet";
    public static final String ROOM_TYPE_DTO_LIST = "roomTypeDtoList";
    public static final String ROOM_TYPE_ID_SET = "roomTypeIdSet";

    //Room parameters

    public static final String ROOM = "room";
    public static final String ROOM_ID = "roomId";
    public static final String ROOM_HOTEL = "roomHotel";
    public static final String ROOM_ROOM_TYPE = "roomType";
    public static final String ROOM_NUMBER = "roomNumber";
    public static final String ROOM_BOOKING_START_DATE = "bookingStartDate";
    public static final String ROOM_BOOKING_END_DATE = "bookingEndDate";
    public static final String ROOM_STATUS = "roomStatus";
    public static final String ROOM_LIST = "roomList";
    public static final String ROOM_DTO_LIST = "roomDtoList";

    //Command attributes

    public static final String COMMAND = "command";
    public static final String SUB_COMMAND = "subCommand";
    public static final String ALTER_TABLE_COMMAND = "alterTable";

    //General attributes

    public static final String ERROR_DATABASE = "errorDatabase";
    public static final String ERROR_LOGIN_OR_PASSWORD = "errorLoginOrPassword";
    public static final String ERROR_USER_EXISTS = "errorUserExists";
    public static final String OPERATION_MESSAGE = "operationMessage";
    public static final String CURRENT_PAGE_PATH = "currentPagePath";
    public static final String IS_AJAX_REQUEST = "isAjaxRequest";
    public static final String FILTER_PARAMETER = "filterParameter";
    public static final String FILTER_PARAMETER_VALUE = "filterParameterValue";
    public static final String FIELD_VALUES_MAP = "fieldValuesMap";
    public static final String STATUS_LIST = "statusList";
    public static final String DATA_MAP = "dataMap";
    public static final String ENTITY = "entity";
    public static final String ENTITY_MAP = "entityMap";
    public static final String ENTITY_LIST = "entityList";
    public static final String DESCRIPTOR_LIST = "descriptorList";
    public static final String ENTITY_DTO_LIST = "entityDtoList";
    public static final String FORM_NAME = "formName";
    public static final String DESCRIPTOR = "descriptor";

    //Database redactor parameters

    public static final String TABLE_REDACTOR_TYPE = "tableRedactorType";
    public static final String FIELD_ORDER = "fieldOrder";

    //Localisation parameters

    public static final String TEXT_MAP = "textMap";

    private Parameter() {
    }
}
