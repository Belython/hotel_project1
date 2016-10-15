package by.kanarski.booking.utils.field;

import java.util.LinkedHashMap;

public class FieldDescriptor<T> {

    private String fieldType;
    private Iterable<T> allowedValues;
    private LinkedHashMap<String, FieldDescriptor> fieldMap;
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

    public LinkedHashMap<String, FieldDescriptor> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(LinkedHashMap<String, FieldDescriptor> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public T getOwner() {
        return owner;
    }

    public void setOwner(T owner) {
        this.owner = owner;
    }
}
