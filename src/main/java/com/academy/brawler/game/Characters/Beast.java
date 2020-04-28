package com.academy.brawler.game.Characters;

import com.academy.brawler.game.Attributes;

import java.io.InvalidObjectException;

public class Beast implements Fighter {
    private String name;
    private Attributes attributes;
    private int currentHealth;
    private Equipment equipment;
    private int maxHealth;

    public Beast(final String beastName, final Attributes gladiatorAttributes, final Equipment gladiatorEquipment){
        this.name = beastName;
        this.attributes = gladiatorAttributes;
        this.equipment = gladiatorEquipment;
        this.maxHealth = (int) Math.round(gladiatorAttributes.getHealth() * 0.8 + gladiatorAttributes.getStrength() * 0.4 + gladiatorAttributes.getEndurance() * 0.1);
        this.currentHealth = maxHealth;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public int getActionsPerTurn() throws InvalidObjectException {
        return equipment.getActionsPerTurn();
    }

    @Override
    public Equipment getEquipment() {
        return equipment;
    }

    @Override
    public int getCurrentHealth() {
        return currentHealth;
    }

    @Override
    public void takeDamage(final int damage) {
        currentHealth -= damage;
    }

}
