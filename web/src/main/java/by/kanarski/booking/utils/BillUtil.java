package by.kanarski.booking.utils;

import by.kanarski.booking.dto.RoomDto;
import by.kanarski.booking.exceptions.LocalisationException;

import java.util.List;
import java.util.Locale;

public class BillUtil {

    public static double getPaymentAmount(int bookedDays, List<RoomDto> roomDtoList) {
        double payment = 0;
        for (RoomDto roomDto : roomDtoList) {
            double part = roomDto.getRoomType().getPricePerNight() * bookedDays;
            payment += part;
        }
        return payment;
    }

    public static int getBookedDays(String formattedCheckInDate, String formattedCheckOutDate)
            throws LocalisationException {
        Locale locale = UserPreferences.getLocale();
        final long MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000;
        long date1 = DateUtil.parseDate(formattedCheckInDate, locale);
        long date2 = DateUtil.parseDate(formattedCheckOutDate, locale);
        int bookedDays = Math.round((date2 - date1) / MILLISECONDS_IN_DAY);
        return bookedDays;
    }

}
