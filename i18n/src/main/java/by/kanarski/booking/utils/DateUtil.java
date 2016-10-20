package by.kanarski.booking.utils;

import by.kanarski.booking.constants.L10nMessage;
import by.kanarski.booking.exceptions.LocalisationException;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.NavigableSet;
import java.util.TreeMap;

public class DateUtil {

    private DateUtil() {
    }

    public static String getFormattedDate(long date, Locale locale) {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        String formattedDate = dateFormat.format(new Date(date));
        return formattedDate;
    }

    public static long parseDate(String formattedDate, Locale locale) throws LocalisationException {
        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, locale);
        long date = 0;
        try {
            date = dateFormat.parse(formattedDate).getTime();
        } catch (ParseException e) {
            throw new LocalisationException(L10nMessage.PARSE_DATE_EXCEPTION, e);
        }
        return date;
    }

    public static TreeMap<String, String> localizeBookedDates(TreeMap<Long, Long> bookedDates, Locale locale)
            throws LocalisationException {
        TreeMap<String, String> localizedBookedDates = new TreeMap<>();
        if (bookedDates != null) {
            NavigableSet<Long> bookingStartDates = bookedDates.navigableKeySet();
            for (Long bookingStartDate : bookingStartDates) {
                Long bookingEndDate = bookedDates.get(bookingStartDate);
                String localizedBookingStartDate = DateUtil.getFormattedDate(bookingStartDate, locale);
                String localizedBookingEndDate = DateUtil.getFormattedDate(bookingEndDate, locale);
                localizedBookedDates.put(localizedBookingStartDate, localizedBookingEndDate);
            }
        }
        return localizedBookedDates;
    }

    public static TreeMap<Long, Long> delocalizeBookedDates(TreeMap<String, String> bookedDates, Locale locale)
            throws LocalisationException {
        TreeMap<Long, Long> delocalizedBookedDates = new TreeMap<>();
        if (bookedDates != null) {
            NavigableSet<String> bookingStartDates = bookedDates.navigableKeySet();
            for (String bookingStartDate : bookingStartDates) {
                String bookingEndDate = bookedDates.get(bookingStartDate);
                Long delocalizedBookingStartDate = DateUtil.parseDate(bookingStartDate, locale);
                Long delocalizedBookingEndDate = DateUtil.parseDate(bookingEndDate, locale);
                delocalizedBookedDates.put(delocalizedBookingStartDate, delocalizedBookingEndDate);
            }
        }
        return delocalizedBookedDates;
    }



}
