package by.kanarski.booking.utils;

import by.kanarski.booking.constants.Messages;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class LocalizationUtil {

    private static final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;

    private LocalizationUtil() {
    }

    public static String getFormattedDate(long date, Locale locale) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        String formattedDate = dateFormat.format(new Date(date));
        return formattedDate;
    }

    public static long parseDate(String formattedDate, Locale locale) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        long date = 0;
        try {
            date = dateFormat.parse(formattedDate).getTime();
        } catch (ParseException e) {
            BookingSystemLogger.getInstance().logError(LocalizationUtil.class, Messages.PARSE_DATE_EXCEPTION + e);
        }
        return date;
    }

    public static int getDays(long date1, long date2) {
        int days = Math.round((date2 - date1) / MILLISECONDS_IN_DAY);
        return days;
    }


}
