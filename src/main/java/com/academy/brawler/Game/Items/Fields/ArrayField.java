package com.academy.brawler.Game.Items.Fields;


import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayField<Type> extends Field {
    private List<Type> values = new ArrayList<>();
    private List<Type> defaultValues = new ArrayList<>();

    public ArrayField(final FieldName fieldName, final boolean fieldRequired) {
        super(fieldName, fieldRequired);
    }

    public ArrayField(final FieldName fieldName, final List<Type> fieldDefaultValues) {
        super(fieldName, true);
        this.defaultValues = fieldDefaultValues;
    }

    public void setValue(final List<Type> fieldValues) {
        this.values = fieldValues;
    }

    public void addValue(final Type fieldValue) {
        values.add(fieldValue);
    }

    public List<Type> getValues() {
        if (isValid()) {
            if (values.isEmpty() && !defaultValues.isEmpty()) {
                return defaultValues;
            } else {
                return values;
            }
        } else {
            throw new IllegalArgumentException(String.format("Field '%s' is required but is not set.", getName()));
        }
    }

    @Override
    public ObjectNode asJson() {
        ObjectNode node = getMapper().createObjectNode();
        node.put("name", getName().name());
        node.put("required", isRequired());
        node.put("defaultValues", defaultValues.stream().map(Object::toString).collect(Collectors.joining(",")));
        node.put("values", values.stream().map(Object::toString).collect(Collectors.joining(",")));

        return node;
    }

    @Override
    public boolean isValid() {
        return !(isRequired() && values.isEmpty() && defaultValues.isEmpty());
    }

    @Override
    public void clearAllValues() {
        values.clear();
    }
}
