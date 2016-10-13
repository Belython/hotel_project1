package by.kanarski.booking.utils.field;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Field<T> {

    private String fieldType;
    private Iterable<T> allowedValues;
    private LinkedHashMap<String, Field> fieldMap;
    private T owner;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public Iterable<T> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(Iterable<T> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public LinkedHashMap<String, Field> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(LinkedHashMap<String, Field> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public T getOwner() {
        return owner;
    }

    public void setOwner(T owner) {
        this.owner = owner;
    }
}
