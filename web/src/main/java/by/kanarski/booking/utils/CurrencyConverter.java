package by.kanarski.booking.utils;

import java.util.Currency;

/**
 * Curreny converter
 * @author Dzmitry Kanarski
 * @version 1.0
 */

public class CurrencyConverter {

    public static double convert(double amount, Currency from, Currency to) {
        double exchangeRate = getExchangeRate(from, to);
        double convertedAmount = amount * exchangeRate;
        return convertedAmount;
    }

    private static double getExchangeRate(Currency from, Currency to) {
        double rate = 1;
        return rate;
    }

}
