package com.academy.brawler.game.Items.Fields;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class SingleField<Type> extends Field {
    private Type value;
    private Type defaulValue;

    public SingleField(final FieldName fieldName, final boolean fieldRequired) {
        super(fieldName, fieldRequired);
    }

    public SingleField(final FieldName fieldName, final Type fieldDefaultValue) {
        super(fieldName, true);
        this.defaulValue = fieldDefaultValue;
    }

    public Type getValue() {
        if (isValid()) {
            if (value == null && defaulValue != null) {
                return defaulValue;
            } else {
                return value;
            }
        } else {
            requiredFieldIsEmpty(getName());
            return null;
        }
    }

    public void setValue(final Type fieldValue) {
        this.value = fieldValue;
    }

    @Override
    public ObjectNode asJson() {
        ObjectNode node = getMapper().createObjectNode();
        node.put("name", getName().name());
        node.put("required", isRequired());
        if (defaulValue != null){
            node.put("defaultValue", defaulValue.toString());
        }
        if (value != null){
            node.put("value", value.toString());
        }

        return node;
    }

    @Override
    public boolean isValid() {
        return !(isRequired() && value == null && defaulValue == null);
    }

    @Override
    public void clearAllValues() {
        value = null;
    }

}
