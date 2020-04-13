package com.academy.brawler.Game.Items.Types.Weapons;

import com.academy.brawler.Game.Items.Fields.ArrayField;
import com.academy.brawler.Game.Items.Fields.FieldName;
import com.academy.brawler.Game.Items.Fields.SingleField;
import com.academy.brawler.Game.Items.ItemSlot;

import static com.academy.brawler.Game.Items.Fields.FieldName.IS_TWO_HANDED;
import static com.academy.brawler.Game.Items.Fields.FieldName.ITEM_SLOTS;


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
