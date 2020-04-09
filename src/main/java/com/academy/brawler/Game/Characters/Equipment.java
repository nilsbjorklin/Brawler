package com.academy.brawler.Game.Characters;

import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.Types.ItemSlot;

public class Equipment {
    private Item mainHand;
    private Item offHand;
    private Item armor;
    private Item[] trinkets = new Item[2];

    public Equipment() {
    }

    public Item getMainHand() {
        return mainHand;
    }

    public Item getOffHand() {
        return offHand;
    }

    public Item getArmor() {
        return armor;
    }

    public Item[] getTrinkets() {
        return trinkets;
    }

    public void setMainHand(final Item mainHandItem) {
        if (mainHandItem.itemMatchesItemSlots(ItemSlot.MAIN_HAND)){
            this.offHand = mainHandItem;
        }
    }

    public void setOffHand(final Item offHandItem) {
        if (offHandItem.itemMatchesItemSlots(ItemSlot.OFF_HAND)){
            this.offHand = offHandItem;
        }
    }

    public void setArmor(final Item armorItem) {
        if (armorItem.itemMatchesItemSlots(ItemSlot.BODY)){
            this.offHand = armorItem;
        }
    }

    public void setTrinket(final Item trinketItem, final int trinketNumber) {
        if (trinketItem.itemMatchesItemSlots(ItemSlot.TRINKET)){
            this.trinkets[trinketNumber] = trinketItem;
        }
    }
}