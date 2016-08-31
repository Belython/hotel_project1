package by.kanarski.booking.constants;

import java.util.Arrays;
import java.util.List;

public class PageTextContentName {

    public static final List<String> INDEX = Arrays.asList(
            "index.rooms_amount",
            "index.persons_amount",
            "index.check_in_date",
            "index.check_out_date"
    );

    public static final List<String> REGISTRATION = Arrays.asList(
            "registration.enter_your_date",
            "registration.first_name",
            "registration.last_name",
            "registration.email",
            "registration.login",
            "registration.password",
            "registration.go_back"
    );

    public static final List<String> ACCOUNT = Arrays.asList(

    );

    public static final List<String> ERROR = Arrays.asList(

    );

    public static final List<String> SELECT_HOTEL = Arrays.asList(
            "select_hotel.hotel_name",
            "select_hotel.rooms_available",
            "select_hotel.go_back"
    );

    public static final List<String> SELECT_ROOM = Arrays.asList(
            "select_room.go_back"
    );

    public static final List<String> HEADER = Arrays.asList(
            "header.login",
            "header.password",
            "header.sign_in",
            "header.sing_out",
            "header.register",
            "header.forgot_password"
    );

    private PageTextContentName() {
    }



}
