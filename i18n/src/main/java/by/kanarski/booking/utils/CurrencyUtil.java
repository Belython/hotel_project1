package by.kanarski.booking.utils;

import java.util.Currency;
import java.util.Locale;

/**
 * Curreny converter
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class CurrencyUtil {

    public static double convert(double amount, Currency from, Currency to) {
        double exchangeRate = getExchangeRate(from, to);
        double convertedAmount = amount * exchangeRate;
        return convertedAmount;
    }

    public static double convertToUSD(double amount, Currency from) {
        double convertedAmount = convert(amount, from, Currency.getInstance(new Locale("en", "US")));
        return convertedAmount;
    }

    public static double convertFromUSD(double amount, Currency to) {
        double convertedAmount = convert(amount, Currency.getInstance(new Locale("en", "US")), to);
        return convertedAmount;
    }

    private static double getExchangeRate(Currency from, Currency to) {
        double rate = 1;
        return rate;
    }

}
