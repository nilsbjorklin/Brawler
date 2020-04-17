package com.academy.brawler.game.Items.Types;

import com.academy.brawler.game.Items.Fields.FieldName;
import com.academy.brawler.game.Items.Item;
import com.academy.brawler.game.Items.ItemSlot;
import com.academy.brawler.game.Items.Types.Weapons.WeaponType;

import java.io.InvalidObjectException;

import static com.academy.brawler.game.Items.Fields.FieldName.*;

public class Shield extends Item {

    public Shield() throws InvalidObjectException {
        super();
        createField(FieldName.ABSORBTION, true, false, Integer.class);
        createField(FieldName.BREAK_VALUE, true, false, Integer.class);
        createField(FieldName.WEAPON_TYPE, true, false, WeaponType.class);
        createField(FieldName.BREAK_VALUE, true, false, Integer.class);

        getArrayItemSlotField(ITEM_SLOTS).addValue(ItemSlot.OFF_HAND);

        getSingleWeaponTypeField(WEAPON_TYPE).setValue(WeaponType.SHIELD);

    }
}
