package com.academy.brawler.Game.Items.Types.Weapons;

import com.academy.brawler.Game.Items.FieldName;
import com.academy.brawler.Game.Items.ItemSlot;



public class OneHandWeapon extends Weapon {
    public OneHandWeapon(){
        setFields();
        addFieldValues(FieldName.IS_TWO_HANDED, false);
        addFieldValues(FieldName.ITEM_SLOTS, ItemSlot.MAIN_HAND, ItemSlot.OFF_HAND);
    }
}
