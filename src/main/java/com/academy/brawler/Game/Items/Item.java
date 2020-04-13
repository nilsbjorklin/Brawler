package com.academy.brawler.Game.Items;

import com.academy.brawler.Game.Attributes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.academy.brawler.Game.Items.Fields.*;

public interface Item {
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<Field> fields = new ArrayList<>();

    default void setFields() {
        fields.add(new Field<String>(NAME_TAG, true));
        fields.add(new Field<String>(DESCRIPTION_TAG, true));
        fields.add(new Field<Long>(WEIGHT_TAG, true));
        fields.add(new Field<ItemSlot>(ITEM_SLOTS_TAG, true));
        fields.add(new Field<Attributes>(ATTRIBUTES_TAG, true));
        fields.add(new Field<Attributes>(REQUIREMENTS_TAG, true));
    }

    default boolean itemMatchesItemSlots(final ItemSlot itemSlot) {
        List<ItemSlot> itemSlots = (List<ItemSlot>) getField(ITEM_SLOTS_TAG).getValues();
        for (ItemSlot slot : itemSlots) {
            if (slot.equals(itemSlot)) {
                return true;
            }
        }
        return false;
    }

    default void checkFields() throws MissingFieldException {
        for (final Field field : fields) {
            if (field.numberOfValues() == 0 && field.isRequired()) {
                throw new MissingFieldException(field);
            }
        }
    }

    default String[] getFieldNames() {
        return fields.stream().map(Field::getName).collect(Collectors.joining(",")).split(",");
    }

    default ObjectNode asJson() {
        ArrayNode node = mapper.createArrayNode();

        for (final Field field : fields) {
            node.add(field.asJson());
        }
        return mapper.createObjectNode().set(this.getClass().getSimpleName(), node);
    }


    default String asString() {
        return asJson().toString();
    }

    default Item addFieldValues(final String fieldName, final Object... fieldValues) {
        for (Object fieldValue : fieldValues) {
            getField(fieldName).addValue(fieldValue);
        }
        return this;
    }

    default Field getField(final String fieldName) {
        for (final Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    class Field<Type> {
        private String name;
        private List<Type> values;
        private boolean required;

        public Field(final String fieldName, final boolean fieldRequired) {
            this.name = fieldName;
            this.required = fieldRequired;
            values = new ArrayList<>();
        }

        public ObjectNode asJson() {
            ObjectNode node = mapper.createObjectNode();
            node.put("name", this.name);
            switch (values.size()) {
                case 0:
                    node.put("value", "null");
                case 1:
                    node.set("value", getValueAsJson(values.get(0)));
                    break;
                default:
                    ArrayNode arrayNode = mapper.createArrayNode();
                    for (Type value : values) {
                        arrayNode.add(getValueAsJson(value));
                    }
            }


            node.put("required", this.required);

            return node;
        }

        private ObjectNode getValueAsJson(final Type value) {
            ObjectNode node = mapper.createObjectNode();
            if (this.values == null) {
                node.put("value", "null");
            } else {
                if (value.getClass().equals(Attributes.class)) {
                    node.set("value", ((Attributes) value).asJson());
                } else {
                    node.put("value", value.toString());
                }
            }
            return node;
        }

        public void addValue(final Type value) {
            this.values.add(value);
        }

        public String getName() {
            return name;
        }

        public int numberOfValues() {
            return values.size();
        }


        public List<Type> getValues() {
            return values;
        }

        public boolean isRequired() {
            return required;
        }

        @Override
        public String toString() {
            return asJson().toString();
        }
    }

    class MissingFieldException extends Exception {
        public MissingFieldException(final Field field) {
            super(String.format("Required field '´%s' is missing", field.getName()));
        }
    }
}