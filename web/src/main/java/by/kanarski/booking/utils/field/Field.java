package by.kanarski.booking.utils.field;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Field<T> {

    private String fieldType;
    private List<T> allowedValues;
    private LinkedHashMap<String, Field> fieldMap;
    private T owner;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public List<T> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(List<T> allowedValues) {
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
