package com.academy.brawler.game.Characters;

import com.academy.brawler.game.Items.Item;
import com.academy.brawler.game.Items.ItemSlot;
import com.academy.brawler.game.Items.Types.Armor;
import com.academy.brawler.game.Items.Types.Shield;
import com.academy.brawler.game.Items.Types.Weapons.Weapon;

public class Equipment {
    private Weapon mainHand;
    private Shield shield;
    private Weapon offHand;
    private Armor armor;
    private Item[] trinkets = new Item[2];

    public Equipment() {
    }

    public Weapon getMainHand() {
        return mainHand;
    }

    public Weapon getOffHand() {
        return offHand;
    }

    public Shield getShield() {
        return shield;
    }

    public Armor getArmor() {
        return armor;
    }

    public Item[] getTrinkets() {
        return trinkets;
    }

    public void setMainHand(final Weapon mainHandItem) {
        if (mainHandItem.itemMatchesItemSlots(ItemSlot.MAIN_HAND)){
            this.mainHand = mainHandItem;
        }
    }

    public void setOffHand(final Weapon offHandItem) {
        if (offHandItem.itemMatchesItemSlots(ItemSlot.OFF_HAND)){
            this.offHand = offHandItem;
        }
    }

    public void setOffHand(final Shield shield) {
        if (shield.itemMatchesItemSlots(ItemSlot.OFF_HAND)){
            this.shield = shield;
        }
    }

    public void setArmor(final Armor armorItem) {
        if (armorItem.itemMatchesItemSlots(ItemSlot.BODY)){
            this.armor = armorItem;
        }
    }

    public void setTrinket(final Item trinketItem, final int trinketNumber) {
        if (trinketItem.itemMatchesItemSlots(ItemSlot.TRINKET)){
            this.trinkets[trinketNumber] = trinketItem;
        }
    }
}