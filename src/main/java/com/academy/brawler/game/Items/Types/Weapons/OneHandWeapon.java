package com.academy.brawler.game.Items.Types.Weapons;

import com.academy.brawler.game.Items.Fields.ArrayField;
import com.academy.brawler.game.Items.Fields.SingleField;
import com.academy.brawler.game.Items.ItemSlot;

import static com.academy.brawler.game.Items.Fields.FieldName.IS_TWO_HANDED;
import static com.academy.brawler.game.Items.Fields.FieldName.ITEM_SLOTS;


public class OneHandWeapon extends Weapon {
    public OneHandWeapon(){
        super();
        SingleField<Boolean> isTwoHanded = (SingleField<Boolean>)getField(IS_TWO_HANDED);
        isTwoHanded.setValue(false);

        ArrayField<ItemSlot> itemSlots = (ArrayField<ItemSlot>)getField(ITEM_SLOTS);
        itemSlots.addValue(ItemSlot.MAIN_HAND);
        itemSlots.addValue(ItemSlot.OFF_HAND);
    }
}