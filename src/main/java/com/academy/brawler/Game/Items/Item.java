package com.academy.brawler.Game.Items;

import com.academy.brawler.Game.Attributes;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Item {
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<Field> fields = new ArrayList<>();

    default void setFields() {
        fields.add(new Field<String>(FieldName.NAME, true));
        fields.add(new Field<String>(FieldName.DESCRIPTION, true));
        fields.add(new Field<Long>(FieldName.WEIGHT, true));
        fields.add(new Field<ItemSlot>(FieldName.ITEM_SLOTS, true));
        fields.add(new Field<Attributes>(FieldName.ATTRIBUTES, true));
        fields.add(new Field<Attributes>(FieldName.REQUIREMENTS, true));
    }

    default boolean itemMatchesItemSlots(final ItemSlot itemSlot) {
        List<ItemSlot> itemSlots = (List<ItemSlot>) getField(FieldName.ITEM_SLOTS).getValues();
        for (ItemSlot slot : itemSlots) {
            if (slot.equals(itemSlot)) {
                return true;
            }
        }
        return false;
    }

    default void checkFields() throws MissingFieldException {
        System.out.println(getFieldNames().toPrettyString());
        for (final Field field : fields) {
            if (field.numberOfValues() == 0 && field.isRequired()) {
                System.err.printf("Field %s not found, value: %s.%n", field.getName(), field.getValue());
                throw new MissingFieldException(field);
            } else {
                System.out.printf("Field %s found, value: %s.%n", field.getName(), field.getValue());
            }
        }
    }

    default ObjectNode getFieldNames() {
        ArrayNode requireFields = mapper.createArrayNode();
        ArrayNode optionalFields = mapper.createArrayNode();
        fields.forEach(field -> {
            if (field.isRequired()){
                requireFields.add(field.name.name());
            } else {
                optionalFields.add(field.name.name());
            }
        });
        ObjectNode node = mapper.createObjectNode();
        node.set("required", requireFields);
        node.set("optional", optionalFields);
        return node;
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

    default Item addFieldValues(final FieldName fieldName, final Object... fieldValues) {
        for (Object fieldValue : fieldValues) {
            getField(fieldName).addValue(fieldValue);
        }
        return this;
    }

    default Field getField(final FieldName fieldName) {
        for (final Field field : fields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        return null;
    }

    class Field<Type> {
        private FieldName name;
        private List<Type> values;
        private boolean required;

        public Field(final FieldName fieldName, final boolean fieldRequired) {
            this.name = fieldName;
            this.required = fieldRequired;
            values = new ArrayList<>();
        }

        public ObjectNode asJson() {
            ObjectNode node = mapper.createObjectNode();

            node.put("name", this.name.name());
            switch (values.size()) {
                case 0:
                    node.put("value", "null");
                    break;
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

        public FieldName getName() {
            return name;
        }

        public int numberOfValues() {
            return values.size();
        }


        public Type getValue() {
            if (values.size() == 0){
                return null;
            } else if (values.size() > 1){
                System.err.printf("To many values found for field %s%nValues: %s%n", getName(), values.stream().map(value -> value.toString()).collect(Collectors.joining(", ")));
                throw new NullPointerException();
            } else {
                return values.get(0);
            }
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
            super(String.format("Required field '%s' is missing", field.getName()));
        }
    }
}