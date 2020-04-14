package com.academy.brawler.Game;

import com.academy.brawler.Game.Items.Fields.Field;
import com.academy.brawler.Game.Items.Types.Weapons.WeaponType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.TreeMap;

public class Attributes {
    private static final String HEALTH = "health";
    private static final String LEVEL = "level";
    private static final String ENDURANCE = "endurance";
    private static final String STRENGTH = "strength";
    private static final String INITIATIVE = "initiative";
    private static final String DODGE = "dodge";
    TreeMap<String, Integer> attributeMap;
    private ObjectMapper mapper = new ObjectMapper();

    public Attributes() {
        attributeMap = new TreeMap<>();
        attributeMap.put(LEVEL, 1);
        attributeMap.put(HEALTH, 0);
        attributeMap.put(ENDURANCE, 0);
        attributeMap.put(STRENGTH, 0);
        attributeMap.put(INITIATIVE, 0);
        attributeMap.put(DODGE, 0);
        attributeMap.put(WeaponType.SWORD.value(), 0);
        attributeMap.put(WeaponType.DAGGER.value(), 0);
        attributeMap.put(WeaponType.AXE.value(), 0);
        attributeMap.put(WeaponType.SPEAR.value(), 0);
        attributeMap.put(WeaponType.SHIELD.value(), 0);
        attributeMap.put(WeaponType.MACE.value(), 0);
    }

    public boolean isValid(){
        attributeMap.forEach((name, value) -> {
            Field.checkValueNotNegative(name, value);
        });
        return true;
    }

    public int getHealth() {
        return attributeMap.get(HEALTH);
    }

    public int getEndurance() {
        return attributeMap.get(ENDURANCE);
    }

    public int getStrength() {
        return attributeMap.get(STRENGTH);
    }

    public int getInitiative() {
        return attributeMap.get(INITIATIVE);
    }

    public int getDodge() {
        return attributeMap.get(DODGE);
    }

    public int getSword() {
        return attributeMap.get(WeaponType.SWORD.value());
    }

    public int getDagger() {
        return attributeMap.get(WeaponType.DAGGER.value());
    }

    public int getAxe() {
        return attributeMap.get(WeaponType.AXE.value());
    }

    public int getSpear() {
        return attributeMap.get(WeaponType.SPEAR.value());
    }

    public int getShield() {
        return attributeMap.get(WeaponType.SHIELD.value());
    }

    public int getMace() {
        return attributeMap.get(WeaponType.MACE.value());
    }

    private Attributes setAttribute(final String tag, final int value) {
        attributeMap.put(tag, value);
        return this;

    }

    public int getLevel() {
        return attributeMap.get(LEVEL);
    }

    public Attributes level(final int value) {
        return setAttribute(LEVEL, value);
    }

    public Attributes health(final int value) {
        return setAttribute(HEALTH, value);
    }

    public Attributes endurance(final int value) {
        return setAttribute(ENDURANCE, value);
    }

    public Attributes strength(final int value) {
        return setAttribute(STRENGTH, value);
    }

    public Attributes initiative(final int value) {
        return setAttribute(INITIATIVE, value);
    }

    public Attributes dodge(final int value) {
        return setAttribute(DODGE, value);
    }

    public Attributes sword(final int value) {
        return setAttribute(WeaponType.SWORD.value(), value);
    }

    public Attributes dagger(final int value) {
        return setAttribute(WeaponType.DAGGER.value(), value);
    }

    public Attributes axe(final int value) {
        return setAttribute(WeaponType.AXE.value(), value);
    }

    public Attributes spear(final int value) {
        return setAttribute(WeaponType.SPEAR.value(), value);
    }

    public Attributes shield(final int value) {
        return setAttribute(WeaponType.SHIELD.value(), value);
    }

    public Attributes mace(final int value) {
        return setAttribute(WeaponType.MACE.value(), value);
    }

    public ObjectNode asJson() {
        ObjectNode node = mapper.createObjectNode();
        attributeMap.forEach((name, value) -> {
            if (value != 0) {
                node.put(name, value);
            }
        });

        return node;
    }

    @Override
    public String toString() {
        return asJson().toString();
    }
}