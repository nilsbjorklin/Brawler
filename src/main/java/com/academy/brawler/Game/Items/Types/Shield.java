package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Fields.ArrayField;
import com.academy.brawler.Game.Items.Fields.FieldName;
import com.academy.brawler.Game.Items.Fields.SingleField;
import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.ItemSlot;
import com.academy.brawler.Game.Items.Types.Weapons.WeaponType;

import static com.academy.brawler.Game.Items.Fields.FieldName.*;

public class Shield extends Item {

    public Shield() {
        super();
        setField(FieldName.ABSORBTION, new SingleField<Integer>(FieldName.ABSORBTION, true));
        setField(FieldName.BREAK_VALUE, new SingleField<Integer>(FieldName.BREAK_VALUE, true));
        setField(FieldName.WEAPON_TYPE, new SingleField<WeaponType>(FieldName.WEAPON_TYPE, true));
        setField(FieldName.BREAK_VALUE, new SingleField<Integer>(FieldName.BREAK_VALUE, true));


        ArrayField<ItemSlot> itemSlots = (ArrayField<ItemSlot>)getField(ITEM_SLOTS);
        itemSlots.addValue(ItemSlot.OFF_HAND);

        SingleField<WeaponType> weaponType = (SingleField<WeaponType>)getField(WEAPON_TYPE);
        weaponType.setValue(WeaponType.SHIELD);

    }
}
