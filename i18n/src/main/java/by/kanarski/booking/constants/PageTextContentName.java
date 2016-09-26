package by.kanarski.booking.constants;

import java.util.Arrays;
import java.util.List;

public class PageTextContentName {

    public static final List<String> INDEX = Arrays.asList(
            "index.booking",
            "index.searchParameters",
            "index.country",
            "index.city",
            "index.hotel",
            "index.allHotels",
            "index.roomsAmount",
            "index.personsAmount",
            "index.checkInDate",
            "index.checkOutDate",
            "index.searchRooms"
    );

    public static final List<String> REGISTRATION = Arrays.asList(
            "registration.registration",
            "registration.enterYourData",
            "registration.firstName",
            "registration.lastMame",
            "registration.email",
            "registration.login",
            "registration.password",
            "registration.register"
    );

    public static final List<String> ACCOUNT = Arrays.asList(
            "account.account",
            "account.country",
            "account.city",
            "account.hotelName",
            "account.roomTypeName",
            "account.maxPersons",
            "account.totalPersons",
            "account.roomsAmount",
            "account.checkInDate",
            "account.checkOutDate",
            "account.paymentAmount",
            "account.billStatus",
            "account.payBill",
            "account.refuseBill"
    );

    // TODO: 22.09.2016 Локализация error page не работает, после форварда от нее index.jsp не локализуется 

    public static final List<String> ERROR = Arrays.asList(
            "error.error",
            "error.sorry",
            "error.goToMain"
    );

    public static final List<String> SELECT_HOTEL = Arrays.asList(
            "selectHotel.selectHotel",
            "selectHotel.сountry",
            "selectHotel.сity",
            "selectHotel.hotelName",
            "selectHotel.roomsAvailable",
            "selectHotel.submitHotel"
    );

    public static final List<String> SELECT_ROOM = Arrays.asList(
            "selectRoom.selectRoom",
            "selectRoom.roomTypeName",
            "selectRoom.available",
            "selectRoom.submitRooms"
    );

    public static final List<String> HEADER = Arrays.asList(
            "header.login",
            "header.password",
            "header.signIn",
            "header.signOut",
            "header.register",
            "header.forgotPassword",
            "header.goToMain",
            "header.welcome",
            "header.administrator",
            "header.goToAdminPage",
            "header.goToAccount",
            "header.selectLanguage",
            "header.submit"
    );

    public static final List<String> ADMIN_MAIN = Arrays.asList(
            "adminMain.adminMain"
    );

    public static final List<String> SIDE_BAR = Arrays.asList(
            "sideBar.redactHotels",
            "sideBar.redactLocations",
            "sideBar.redactUsers",
            "sideBar.redactRoomTypes",
            "sideBar.redactRooms",
            "sideBar.redactBills",
            "sideBar.submit"
    );

    public static final List<String> ROOMS_REDACTOR = Arrays.asList(
            "roomsRedactor.roomsRedactor",
            "roomsRedactor.roomHotel",
            "roomsRedactor.roomType",
            "roomsRedactor.roomNumber",
            "roomsRedactor.roomStatus",
            "roomsRedactor.country",
            "roomsRedactor.city",
            "roomsRedactor.roomTypeName",
            "roomsRedactor.maxPersons",
            "roomsRedactor.roomPricePerNight",
            "roomsRedactor.createRoom",
            "roomsRedactor.removeRoom",
            "roomsRedactor.addRoom",
            "roomsRedactor.hotelName",
            "roomsRedactor.ascending",
            "roomsRedactor.descending",
            "roomsRedactor.sort",
            "roomsRedactor.alterRoom",
            "roomsRedactor.alterAllRooms"
    );

    public static final List<String> LOCALE_LIST = Arrays.asList(
            "locale.ru_RU",
            "locale.en_US",
            "locale.en_EN"
    );

    private PageTextContentName() {

    }

}
