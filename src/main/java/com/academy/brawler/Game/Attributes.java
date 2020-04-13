package com.academy.brawler.Game;

import com.academy.brawler.Game.Items.Types.Weapons.WeaponType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.TreeMap;

public class Attributes {
    private ObjectMapper mapper = new ObjectMapper();
    TreeMap<String, Long> attributeMap;

    private static final String HEALTH = "health";
    private static final String ENDURANCE = "endurance";
    private static final String STRENGTH = "strength";
    private static final String INITIATIVE = "initiative";
    private static final String DODGE = "dodge";

    public Attributes() {
        attributeMap = new TreeMap<>();
        attributeMap.put(HEALTH, 0L);
        attributeMap.put(ENDURANCE, 0L);
        attributeMap.put(STRENGTH, 0L);
        attributeMap.put(INITIATIVE, 0L);
        attributeMap.put(DODGE, 0L);
        attributeMap.put(WeaponType.SWORD.value(), 0L);
        attributeMap.put(WeaponType.DAGGER.value(), 0L);
        attributeMap.put(WeaponType.AXE.value(), 0L);
        attributeMap.put(WeaponType.SPEAR.value(), 0L);
        attributeMap.put(WeaponType.SHIELD.value(), 0L);
        attributeMap.put(WeaponType.MACE.value(), 0L);
    }

    public long getHealth() {
        return attributeMap.get(HEALTH);
    }

    public long getEndurance() {
        return attributeMap.get(ENDURANCE);
    }

    public long getStrength() {
        return attributeMap.get(STRENGTH);
    }

    public long getInitiative() {
        return attributeMap.get(INITIATIVE);
    }

    public long getDodge() {
        return attributeMap.get(DODGE);
    }

    public long getSword() {
        return attributeMap.get(WeaponType.SWORD.value());
    }

    public long getDagger() {
        return attributeMap.get(WeaponType.DAGGER.value());
    }

    public long getAxe() {
        return attributeMap.get(WeaponType.AXE.value());
    }

    public long getSpear() {
        return attributeMap.get(WeaponType.SPEAR.value());
    }

    public long getShield() {
        return attributeMap.get(WeaponType.SHIELD.value());
    }

    public long getMace() {
        return attributeMap.get(WeaponType.MACE.value());
    }

    private Attributes setAttribute(final String tag, final long value) {
        attributeMap.put(tag, value);
        return this;

    }

    public Attributes health(final long value) {
        return setAttribute(HEALTH, value);
    }

    public Attributes endurance(final long value) {
        return setAttribute(ENDURANCE, value);
    }

    public Attributes strength(final long value) {
        return setAttribute(STRENGTH, value);
    }

    public Attributes initiative(final long value) {
        return setAttribute(INITIATIVE, value);
    }

    public Attributes dodge(final long value) {
        return setAttribute(DODGE, value);
    }

    public Attributes sword(final long value) {
        return setAttribute(WeaponType.SWORD.value(), value);
    }

    public Attributes dagger(final long value) {
        return setAttribute(WeaponType.DAGGER.value(), value);
    }

    public Attributes axe(final long value) {
        return setAttribute(WeaponType.AXE.value(), value);
    }

    public Attributes spear(final long value) {
        return setAttribute(WeaponType.SPEAR.value(), value);
    }

    public Attributes shield(final long value) {
        return setAttribute(WeaponType.SHIELD.value(), value);
    }

    public Attributes mace(final long value) {
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