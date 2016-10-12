package by.kanarski.booking.utils.field;

import java.util.List;
import java.util.Map;

public class Field<T> {

    private String fieldType;
    private List<?> allowedValues;
    private Map<String, Field> fieldMap;
    private T owner;

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public List<?> getAllowedValues() {
        return allowedValues;
    }

    public void setAllowedValues(List<?> allowedValues) {
        this.allowedValues = allowedValues;
    }

    public Map<String, Field> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Field> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public T getOwner() {
        return owner;
    }

    public void setOwner(T owner) {
        this.owner = owner;
    }
}
