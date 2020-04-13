package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.ItemSlot;
import com.academy.brawler.Game.Items.Types.Weapons.WeaponType;

import static com.academy.brawler.Game.Items.FieldName.*;

public class Shield implements Item {

    public Shield() {
        setFields();
        fields.add(new Field<Long>(ABSORBTION, true));
        fields.add(new Field<Long>(BREAK_VALUE, true));
        fields.add(new Field<WeaponType>(WEAPON_TYPE, true));
        addFieldValues(ITEM_SLOTS, ItemSlot.OFF_HAND);
        addFieldValues(WEAPON_TYPE, WeaponType.SHIELD);

    }
}
