package by.kanarski.booking.constants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Дмитрий on 24.09.2016.
 */
public class FieldValue {

    //Id value

    public static final long UNDEFINED_ID = -1;

    //Roles

    public static final List<String> ROLE_LIST = Arrays.asList("client", "admin");
    public static final String ROLE_CLIENT = "client";
    public static final String ROLE_ADMIN = "admin";

    //Statuses

    public static final List<String> STATUS_LIST = Arrays.asList("active", "deleted");
    public static final List<String> BILL_STATUS_LIST = Arrays.asList("paid", "notPaid", "refused", "deleted");
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_DELETED = "deleted";
    public static final String STATUS_PAID = "paid";
    public static final String STATUS_NOT_PAID = "notPaid";
    public static final String STATUS_REFUSED = "refused";

}
