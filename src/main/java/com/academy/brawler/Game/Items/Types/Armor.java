package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Fields.FieldName;
import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.ItemSlot;

import java.io.InvalidObjectException;

import static com.academy.brawler.Game.Items.Fields.FieldName.*;

public class Armor extends Item {

    public Armor() throws InvalidObjectException {
        super();
        createField(FieldName.ABSORBTION, true, false, Integer.class);

        getArrayItemSlotField(ITEM_SLOTS).addValue(ItemSlot.BODY);
    }

}
