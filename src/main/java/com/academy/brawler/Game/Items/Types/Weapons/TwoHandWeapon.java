package com.academy.brawler.Game.Items.Types.Weapons;

import com.academy.brawler.Game.Items.FieldName;
import com.academy.brawler.Game.Items.ItemSlot;

import static com.academy.brawler.Game.Items.FieldName.IS_TWO_HANDED;

public class TwoHandWeapon extends Weapon {
    public TwoHandWeapon(){
        setFields();
        addFieldValues(IS_TWO_HANDED, true);
        addFieldValues(FieldName.ITEM_SLOTS, ItemSlot.MAIN_HAND);
    }
}
