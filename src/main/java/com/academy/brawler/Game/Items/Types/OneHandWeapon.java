package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.ItemSlot;

import static com.academy.brawler.Game.Items.Fields.ITEM_SLOTS_TAG;
import static com.academy.brawler.Game.Items.Fields.TWO_HANDED_TAG;

public class OneHandWeapon extends Weapon {
    public OneHandWeapon(){
        addFieldValues(TWO_HANDED_TAG, false);
        addFieldValues(ITEM_SLOTS_TAG, ItemSlot.MAIN_HAND, ItemSlot.OFF_HAND);
    }
}
