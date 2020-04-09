package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Item;

public class Armor implements Item {
    private String name;
    private String description;
    private long weight;
    private ItemSlot[] itemSlots = new ItemSlot[]{ItemSlot.BODY};
    private long absorbtion;

    public Armor(final String armorName, final String armorDescription, final long armorWeight, final long armorAbsorbtion) {
        this.name = armorName;
        this.description = armorDescription;
        this.weight = armorWeight;
        this.absorbtion = armorAbsorbtion;
    }

    public long getAbsorbtion() {
        return this.absorbtion;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public long getWeight() {
        return this.weight;
    }

    @Override
    public ItemSlot[] getItemSlots() {
        return itemSlots;
    }
}
