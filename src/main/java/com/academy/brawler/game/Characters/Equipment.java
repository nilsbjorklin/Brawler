package com.academy.brawler.game.Characters;

import com.academy.brawler.game.Items.Fields.FieldName;
import com.academy.brawler.game.Items.Item;
import com.academy.brawler.game.Items.ItemSlot;
import com.academy.brawler.game.Items.Types.Armor;
import com.academy.brawler.game.Items.Types.Weapons.WeaponOrShield;

import java.io.InvalidObjectException;
import java.util.List;

public class Equipment {
    private WeaponOrShield mainHand;
    private WeaponOrShield offHand;

    private Armor armor;
    private Item[] trinkets = new Item[2];


    public Equipment() {

    }

    public int getActionsPerTurn() throws InvalidObjectException {
        return mainHand.getSingleField(FieldName.ACTIONS, 0).getValue() + offHand.getSingleField(FieldName.ACTIONS, 0).getValue();
    }

    public void setMainHand(final WeaponOrShield weaponOrShield) throws InvalidObjectException {
        List<ItemSlot> itemSlots = weaponOrShield.getArrayField(FieldName.ITEM_SLOTS, ItemSlot.MAIN_HAND).getValues();
        if (itemSlots.contains(ItemSlot.MAIN_HAND)) {
            this.mainHand = weaponOrShield;
        }
    }

    public void setOffHand(final WeaponOrShield weaponOrShield) throws InvalidObjectException {
        List<ItemSlot> itemSlots = weaponOrShield.getArrayField(FieldName.ITEM_SLOTS, ItemSlot.OFF_HAND).getValues();
        if (itemSlots.contains(ItemSlot.OFF_HAND)) {
            this.mainHand = weaponOrShield;
        }
    }

    public WeaponOrShield getMainHand() {
        return mainHand;
    }

    public WeaponOrShield getOffHand() {
        return offHand;
    }

    public Armor getArmor() {
        return armor;
    }

    public Item[] getTrinkets() {
        return trinkets;
    }

    public void setArmor(final Armor armorItem) throws InvalidObjectException {
        if (armorItem.itemMatchesItemSlots(ItemSlot.BODY)) {
            this.armor = armorItem;
        }
    }

    public void setTrinket(final Item trinketItem, final int trinketNumber) throws InvalidObjectException {
        if (trinketItem.itemMatchesItemSlots(ItemSlot.TRINKET)) {
            this.trinkets[trinketNumber] = trinketItem;
        }
    }
}