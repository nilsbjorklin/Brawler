package com.academy.brawler.Game;

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
    private static final String SWORD = "sword";
    private static final String DAGGER = "dagger";
    private static final String AXE = "axe";
    private static final String SPEAR = "spear";
    private static final String SHIELD = "shield";
    private static final String MACE = "mace";

    public Attributes(){
        attributeMap = new TreeMap<>();
        attributeMap.put(HEALTH, 0L);
        attributeMap.put(ENDURANCE, 0L);
        attributeMap.put(STRENGTH, 0L);
        attributeMap.put(INITIATIVE, 0L);
        attributeMap.put(DODGE, 0L);
        attributeMap.put(SWORD, 0L);
        attributeMap.put(DAGGER, 0L);
        attributeMap.put(AXE, 0L);
        attributeMap.put(SPEAR, 0L);
        attributeMap.put(SHIELD, 0L);
        attributeMap.put(MACE, 0L);
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
        return attributeMap.get(SWORD);
    }

    public long getDagger() {
        return attributeMap.get(DAGGER);
    }

    public long getAxe() {
        return attributeMap.get(AXE);
    }

    public long getSpear() {
        return attributeMap.get(SPEAR);
    }

    public long getShield() {
        return attributeMap.get(SHIELD);
    }

    public long getMace() {
        return attributeMap.get(MACE);
    }

    private Attributes setAttribute(final String tag, final long value){
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
        return setAttribute(SWORD, value);
    }

    public Attributes dagger(final long value) {
        return setAttribute(DAGGER, value);
    }

    public Attributes axe(final long value) {
        return setAttribute(AXE, value);
    }

    public Attributes spear(final long value) {
        return setAttribute(SPEAR, value);
    }

    public Attributes shield(final long value) {
        return setAttribute(SHIELD, value);
    }

    public Attributes mace(final long value) {
        return setAttribute(MACE, value);
    }

    public ObjectNode asJson(){
        ObjectNode node = mapper.createObjectNode();
        attributeMap.forEach(node::put);

        return node;
    }

    @Override
    public String toString() {
        return asJson().toString();
    }
}