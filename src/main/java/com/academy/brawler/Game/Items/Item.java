package com.academy.brawler.Game.Items;

import com.academy.brawler.Game.Attributes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sun.javaws.exceptions.MissingFieldException;

import java.util.ArrayList;

import static com.academy.brawler.Game.Items.Fields.*;

public interface Item {
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<Field> fields = new ArrayList<>();

    default void setFields() {
        fields.add(new Field<String>(NAME_TAG, true));
        fields.add(new Field<String>(DESCRIPTION_TAG, true));
        fields.add(new Field<Long>(WEIGHT_TAG, true));
        fields.add(new Field<ItemSlot[]>(ITEM_SLOTS_TAG, true));
        fields.add(new Field<Attributes>(ATTRIBUTES_TAG, true));
        fields.add(new Field<Attributes>(REQUIREMENTS_TAG, true));
    }

    default boolean itemMatchesItemSlots(final ItemSlot itemSlot) {
        Field field = getField(ITEM_SLOTS_TAG);
        ItemSlot[] itemSlots = (ItemSlot[]) field.getValue();
        for (ItemSlot slot : itemSlots) {
            if (slot.equals(itemSlot)) {
                return true;
            }
        }
        return false;
    }

    default void checkFields() throws MissingFieldException {
        for (final Field field : fields) {
            if (field.getValue() == null && field.isRequired()) {
                throw new MissingFieldException(field.toString(), field.getName());
            }
        }
    }

    default ObjectNode asJson(){
        ArrayNode node = mapper.createArrayNode();

        for (final Field field : fields) {
            node.add(field.asJson());
        }
        return mapper.createObjectNode().set(this.getClass().getSimpleName(), node);
    }


    default String asString(){
        return asJson().toString();
    }

    default void setFieldValue(final String fieldName, final Object fieldValue) {
        getField(fieldName).setValue(fieldValue);
    }

    default Field getField(final String fieldName) {
        for (final Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    static class Field<Type> {
        private String name;
        private Type value;
        private boolean required;

        public Field(final String fieldName, final boolean fieldRequired) {
            this.name = fieldName;
            this.required = fieldRequired;
        }

        public ObjectNode asJson() {
            ObjectNode node = mapper.createObjectNode();
            node.put("name", this.name);
            node.put("value", this.value.toString());
            node.put("required", this.required);
            return node;
        }

        @Override
        public String toString() {
            return asJson().toString();
        }

        public void setValue(final Type value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Type getValue() {
            return value;
        }

        public boolean isRequired() {
            return required;
        }
    }
}