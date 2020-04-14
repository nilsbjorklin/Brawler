package com.academy.brawler.Game.Items.Fields;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class Field {
    private ObjectMapper mapper = new ObjectMapper();
    private FieldName name;
    private boolean required;


    public Field(final FieldName fieldName, final boolean fieldRequired) {
        this.name = fieldName;
        this.required = fieldRequired;
    }

    public static void requiredFieldIsEmpty(final FieldName fieldName) {
        throw new IllegalArgumentException(String.format("Field '%s' is required but is not set.", fieldName.name()));
    }

    public FieldName getName() {
        return name;
    }

    public abstract ObjectNode asJson();

    public abstract boolean isValid();

    public abstract void clearAllValues();

    ObjectMapper getMapper(){
        return mapper;
    }

    public boolean isRequired() {
        return required;
    }

    public static void checkValueLarger(final String lowerValueName, final int lowerValue, final String higherValueName, final int higherValue) {
        if (higherValue < lowerValue) {
            throw new IllegalArgumentException(String.format("Invalid argument, %s=%d cannot be lower than %s=%d.", higherValueName, higherValue, lowerValueName, lowerValue));
        }
    }

    public static void checkValueNotNegative(final String valueName, final int value) {
        if (0 > value) {
            throw new IllegalArgumentException(String.format("Invalid argument, %s cannot be negative but got %d.", valueName, value));
        }
    }
}
