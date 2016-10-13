package by.kanarski.booking.utils.field;

import by.kanarski.booking.entities.Hotel;

import java.util.*;

public class FieldBuilder {

    public static final String PRIMITIVE = "primitive";
    public static final String FREE_PRIMITIVE = "freePrimitive";
    public static final String ITERABLE = "iterable";
    public static final String ENTITY = "entity";

    public static <T> Field<T> buildPrimitive(Iterable<T> allowedValues) {
        Field<T> field = new Field<>();
        field.setFieldType(PRIMITIVE);
        field.setAllowedValues(allowedValues);
        return field;
    }

    public static Field buildFreePrimitive() {
        Field field = new Field();
        field.setFieldType(FREE_PRIMITIVE);
        return field;
    }


    public static Field buildIterable() {
        Field field = new Field();
        field.setFieldType(ITERABLE);
        return field;
    }

    public static <T> Field<T> buildEntity(LinkedHashMap<String, Field> fieldMap, T owner) {
        Field<T> field = new Field<>();
        field.setFieldType(ENTITY);
        field.setFieldMap(fieldMap);
        field.setOwner(owner);
        return field;
    }

}
