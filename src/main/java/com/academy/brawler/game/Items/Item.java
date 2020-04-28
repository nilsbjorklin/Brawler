package com.academy.brawler.game.Items;

import com.academy.brawler.game.Attributes;
import com.academy.brawler.game.Items.Fields.ArrayField;
import com.academy.brawler.game.Items.Fields.Field;
import com.academy.brawler.game.Items.Fields.FieldName;
import com.academy.brawler.game.Items.Fields.SingleField;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.TreeMap;

public abstract class Item {
    private ObjectMapper mapper = new ObjectMapper();
    private TreeMap<FieldName, Field> fields = new TreeMap<>();

    public Item() {
        createSingleField(FieldName.NAME, true, String.class);
        createSingleField(FieldName.DESCRIPTION, true, String.class);
        createSingleField(FieldName.WEIGHT, true, Integer.class);
        createArrayField(FieldName.ITEM_SLOTS, true, ItemSlot.class);
        createSingleField(FieldName.ATTRIBUTES, true, Attributes.class);
        createSingleField(FieldName.REQUIREMENTS, true, Attributes.class);
    }

    public boolean itemMatchesItemSlots(final ItemSlot itemSlot) throws InvalidObjectException {
        ArrayField<ItemSlot> itemSlotArrayField = getArrayField(FieldName.ITEM_SLOTS, itemSlot);
        List<ItemSlot> itemSlots = itemSlotArrayField.getValues();

        for (ItemSlot slot : itemSlots) {
            if (slot.equals(itemSlot)) {
                return true;
            }
        }
        return false;
    }

    public <O> void createSingleField(final FieldName fieldName, final boolean required, final O type) {
        fields.put(fieldName, new SingleField<O>(fieldName, required));
    }

    public <O> void createArrayField(final FieldName fieldName, final boolean required, final O type) {
        fields.put(fieldName, new ArrayField<O>(fieldName, required));
    }

    public <O> void addArrayField(final FieldName fieldName, final O fieldValue) throws InvalidObjectException {
        ArrayField<O> field = getArrayField(fieldName, fieldValue);
        field.addValue(fieldValue);
    }

    public <O> void setSingleField(final FieldName fieldName, final O fieldValue) throws InvalidObjectException {
        SingleField<O> field = getSingleField(fieldName, fieldValue);
        field.setValue(fieldValue);
    }

    public <O> ArrayField<O> getArrayField(final FieldName fieldName, final O object) throws InvalidObjectException {
        Field field = fields.get(fieldName);

        if (field.getClass().equals(ArrayField.class)) {
            return (ArrayField<O>) fields.get(fieldName);
        } else {
            throw new InvalidObjectException(String.format("Found field of type '%s', expected '%s' field.%n", field.getClass(), SingleField.class));
        }
    }


    public <O> SingleField<O> getSingleField(final FieldName fieldName, final O object) throws InvalidObjectException {
        Field field = fields.get(fieldName);

        if (field.getClass().equals(SingleField.class)) {
            return (SingleField<O>) fields.get(fieldName);
        } else {
            throw new InvalidObjectException(String.format("Found field of type '%s', expected '%s' field.%n", field.getClass(), SingleField.class));
        }
    }

    public SingleField<String> getNameField() throws InvalidObjectException {
        return getSingleField(FieldName.NAME, "");
    }

    public void checkFields() {
        fields.forEach((name, field) -> {
            if (!field.isValid()) {
                Field.requiredFieldIsEmpty(name);
            }
        });
    }

    public ObjectNode getFieldNames() {
        ArrayNode requireFields = mapper.createArrayNode();
        ArrayNode optionalFields = mapper.createArrayNode();
        fields.forEach((name, field) -> {
            if (field.isRequired()) {
                requireFields.add(name.name());
            } else {
                optionalFields.add(name.name());
            }
        });
        ObjectNode node = mapper.createObjectNode();
        node.set("required", requireFields);
        node.set("optional", optionalFields);
        return node;
    }

    public ObjectNode asJson() {
        ArrayNode node = mapper.createArrayNode();
        fields.forEach((name, field) -> node.add(field.asJson()));

        return mapper.createObjectNode().set(this.getClass().getSimpleName(), node);
    }
}