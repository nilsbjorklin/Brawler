package com.academy.brawler.game.Characters;

import com.academy.brawler.game.Attributes;

public class Gladiator implements Fighter {
    private String name;
    private Attributes attributes;
    private int currentHealth;
    private Equipment equipment;
    private int maxHealth;

    public Gladiator(final String gladiatorName, final Attributes gladiatorAttributes, final Equipment gladiatorEquipment){
        this.name = gladiatorName;
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
