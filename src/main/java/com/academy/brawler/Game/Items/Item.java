package com.academy.brawler.Game.Items;

import com.academy.brawler.Game.Attributes;
import com.academy.brawler.Game.Items.Fields.ArrayField;
import com.academy.brawler.Game.Items.Fields.Field;
import com.academy.brawler.Game.Items.Fields.FieldName;
import com.academy.brawler.Game.Items.Fields.SingleField;
import com.academy.brawler.Game.Items.Types.Weapons.WeaponType;
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
        createField(FieldName.NAME, true, false, String.class);
        createField(FieldName.DESCRIPTION, true, false, String.class);
        createField(FieldName.WEIGHT, true, false, Integer.class);
        createField(FieldName.ITEM_SLOTS, true, true, ItemSlot.class);
        createField(FieldName.ATTRIBUTES, true, false, Attributes.class);
        createField(FieldName.REQUIREMENTS, true, false, Attributes.class);
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

    public void createField(final FieldName fieldName, final boolean required, final boolean array, final Class type) {
        if (array) {
            createArrayField(fieldName, required, type);
        } else {
            createSingleField(fieldName, required, type);
        }
    }

    public void createSingleField(final FieldName fieldName, final boolean required, final Class type) {
        if (type.equals(Integer.class)) {
            fields.put(fieldName, new SingleField<Integer>(fieldName, required));
        } else if (type.equals(String.class)) {
            fields.put(fieldName, new SingleField<String>(fieldName, required));
        } else if (type.equals(Boolean.class)) {
            fields.put(fieldName, new SingleField<Boolean>(fieldName, required));
        } else if (type.equals(WeaponType.class)) {
            fields.put(fieldName, new SingleField<WeaponType>(fieldName, required));
        } else if (type.equals(Attributes.class)) {
            fields.put(fieldName, new SingleField<Attributes>(fieldName, required));
        } else {
            throw new IllegalArgumentException("Cannot find array of type " + type.getCanonicalName());
        }
    }

    public void createArrayField(final FieldName fieldName, final boolean required, final Class type) {
        if (type.equals(Integer.class)) {
            fields.put(fieldName, new ArrayField<Integer>(fieldName, required));
        } else if (type.equals(String.class)) {
            fields.put(fieldName, new ArrayField<String>(fieldName, required));
        } else if (type.equals(ItemSlot.class)) {
            fields.put(fieldName, new ArrayField<ItemSlot>(fieldName, required));
        } else {
            throw new IllegalArgumentException("Cannot find array of type " + type.getCanonicalName());
        }
    }


    public SingleField<Attributes> getAttributesField(final FieldName fieldName) throws InvalidObjectException {
        Field field = fields.get(fieldName);

        if (field.getClass().equals(SingleField.class)) {
            return (SingleField<Attributes>) fields.get(fieldName);
        } else {
            throw new InvalidObjectException(String.format("Found field of type %s, expected %s field.%n", field.getClass(), SingleField.class));
        }
    }


    public SingleField<String> getSingleStringField(final FieldName fieldName) throws InvalidObjectException {
        Field field = fields.get(fieldName);

        if (field.getClass().equals(SingleField.class)) {
            return (SingleField<String>) fields.get(fieldName);
        } else {
            throw new InvalidObjectException(String.format("Found field of type %s, expected %s field.%n", field.getClass(), SingleField.class));
        }
    }

    public SingleField<Integer> getSingleIntegerField(final FieldName fieldName) throws InvalidObjectException {
        Field field = fields.get(fieldName);

        if (field.getClass().equals(SingleField.class)) {
            return (SingleField<Integer>) fields.get(fieldName);
        } else {
            throw new InvalidObjectException(String.format("Found field of type %s, expected %s field.%n", field.getClass(), SingleField.class));
        }
    }

    public SingleField<Attributes> getSingleAttributesField(final FieldName fieldName) throws InvalidObjectException {
        Field field = fields.get(fieldName);

        if (field.getClass().equals(SingleField.class)) {
            return (SingleField<Attributes>) fields.get(fieldName);
        } else {
            throw new InvalidObjectException(String.format("Found field of type %s, expected %s field.%n", field.getClass(), SingleField.class));
        }
    }

    public SingleField<WeaponType> getSingleWeaponTypeField(final FieldName fieldName) throws InvalidObjectException {
        Field field = fields.get(fieldName);

        if (field.getClass().equals(SingleField.class)) {
            return (SingleField<WeaponType>) fields.get(fieldName);
        } else {
            throw new InvalidObjectException(String.format("Found field of type %s, expected %s field.%n", field.getClass(), SingleField.class));
        }
    }

    public ArrayField<ItemSlot> getArrayItemSlotField(final FieldName fieldName) throws InvalidObjectException {
        Field field = fields.get(fieldName);

        if (field.getClass().equals(ArrayField.class)) {
            return (ArrayField<ItemSlot>) fields.get(fieldName);
        } else {
            throw new InvalidObjectException(String.format("Found field of type %s, expected %s field.%n", field.getClass(), ArrayField.class));
        }
    }


    public SingleField<String> getNameField() throws InvalidObjectException {
        return getSingleStringField(FieldName.NAME);
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