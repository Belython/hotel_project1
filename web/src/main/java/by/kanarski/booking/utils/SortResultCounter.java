package by.kanarski.booking.utils;

/**
 * Created by Дмитрий on 18.09.2016.
 */
public class SortResultCounter {

    public static int getResult(int compareResult, boolean ascending) {
        int result;
        if (compareResult < 0) {
            if (ascending) {
                result = -1;
            } else {
                result = 0;
            }
        } else {
            if (ascending) {
                result = 0;
            } else {
                result = -1;
            }
        }
        return result;
    }

}
