package by.kanarski.booking.constants;

import java.util.Arrays;
import java.util.List;

public class PageTextContentName {

    public static final List<String> INDEX = Arrays.asList(
            "index.roomsAmount",
            "index.personsAmount",
            "index.checkInDate",
            "index.checkOutDate"
    );

    public static final List<String> REGISTRATION = Arrays.asList(
            "registration.enterYourData",
            "registration.firstName",
            "registration.lastMame",
            "registration.email",
            "registration.login",
            "registration.password"
    );

    public static final List<String> ACCOUNT = Arrays.asList(

    );

    public static final List<String> ERROR = Arrays.asList(

    );

    public static final List<String> SELECT_HOTEL = Arrays.asList(
            "selectHotel.hotelName",
            "selectHotel.roomsAvailable"
    );

    public static final List<String> SELECT_ROOM = Arrays.asList(

    );

    public static final List<String> HEADER = Arrays.asList(
            "header.login",
            "header.password",
            "header.signIn",
            "header.signOut",
            "header.register",
            "header.forgotPassword",
            "header.goToMain"
    );

    private PageTextContentName() {
    }



}
