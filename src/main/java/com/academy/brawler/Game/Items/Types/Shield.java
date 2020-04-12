package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.ItemSlot;

import static com.academy.brawler.Game.Items.Fields.ABSORBTION_TAG;
import static com.academy.brawler.Game.Items.Fields.BREAK_VALUE_TAG;
import static com.academy.brawler.Game.Items.Fields.ITEM_SLOTS_TAG;

public class Shield implements Item {

    public Shield() {
        setFields();
        fields.add(new Field<Long>(ABSORBTION_TAG, true));
        fields.add(new Field<Long>(BREAK_VALUE_TAG, true));
        getField(ITEM_SLOTS_TAG).setValue(ItemSlot.OFF_HAND);

    }
}
