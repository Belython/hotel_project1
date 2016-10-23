package by.kanarski.booking.utils;

import by.kanarski.booking.constants.BookingSystemCurrency;
import by.kanarski.booking.constants.BookingSystemLocale;
import by.kanarski.booking.utils.threadLocal.ThreadLocalUtil;

import java.util.Currency;
import java.util.Locale;

/**
 * Contains current user's preferces as thread local variables
 * @author Dzmitry Kanarski
 * @version 1.0
 * @see ThreadLocalUtil
 */

public class UserPreferences {

    private UserPreferences() {

    }

    public static Locale getLocale() {
        Locale locale = (Locale) ThreadLocalUtil.LOCALE.get(BookingSystemLocale.DEFAULT);
        return locale;
    }

    public static void setLocale(Locale locale) {
        ThreadLocalUtil.LOCALE.set(locale);
    }

    public static Currency getCurrency() {
        Currency currency = (Currency) ThreadLocalUtil.CURRENCY.get(BookingSystemCurrency.DEFAULT);
        return currency;
    }

    public static void setCurrency(Currency currency) {
        ThreadLocalUtil.CURRENCY.set(currency);
    }

}
