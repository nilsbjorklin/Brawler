package com.academy.brawler.Game.Items;

import com.academy.brawler.Game.Attributes;
import com.academy.brawler.Game.Items.Fields.ArrayField;
import com.academy.brawler.Game.Items.Fields.Field;
import com.academy.brawler.Game.Items.Fields.FieldName;
import com.academy.brawler.Game.Items.Fields.SingleField;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.TreeMap;

public abstract class Item {
    private ObjectMapper mapper = new ObjectMapper();
    private TreeMap<FieldName, Field> fields = new TreeMap<>();

    public void setField(final FieldName fieldName, final Field field){
        fields.put(fieldName, field);
    }

    public Item() {
        setField(FieldName.NAME, new SingleField<String>(FieldName.NAME, true));
        setField(FieldName.DESCRIPTION, new SingleField<String>(FieldName.DESCRIPTION, true));
        setField(FieldName.WEIGHT, new SingleField<Long>(FieldName.WEIGHT, true));
        setField(FieldName.ITEM_SLOTS, new ArrayField<ItemSlot>(FieldName.ITEM_SLOTS, true));
        setField(FieldName.ATTRIBUTES, new SingleField<Attributes>(FieldName.ATTRIBUTES, true));
        setField(FieldName.REQUIREMENTS, new SingleField<Attributes>(FieldName.REQUIREMENTS, true));
    }

    public boolean itemMatchesItemSlots(final ItemSlot itemSlot) {
        ArrayField<ItemSlot> itemSlotArrayField = (ArrayField<ItemSlot>) fields.get(FieldName.ITEM_SLOTS);
        List<ItemSlot> itemSlots = itemSlotArrayField.getValues();

        for (ItemSlot slot : itemSlots) {
            if (slot.equals(itemSlot)) {
                return true;
            }
        }
        return false;
    }

    public SingleField<String> getNameField(){
        Field field = fields.get(FieldName.NAME);
        if (field.getClass().equals(SingleField.class)){
            return (SingleField<String>) fields.get(FieldName.NAME);
        } else {
            System.err.printf("found field of type %s, expected %s field.%n", field.getClass(), SingleField.class);
            return null;
        }
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

    public Field getField(final FieldName fieldName) {
        return fields.get(fieldName);
    }
}