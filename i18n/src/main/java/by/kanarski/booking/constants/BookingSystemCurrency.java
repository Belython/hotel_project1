package by.kanarski.booking.constants;

import java.util.Currency;

/**
 * Created by dkanarsky on 19.10.2016.
 */
public class BookingSystemCurrency {

    public static final Currency USD = Currency.getInstance("USD");
    public static final Currency BYR = Currency.getInstance("BYR");
    public static final Currency DEFAULT = USD;

    private BookingSystemCurrency() {

    }

}
