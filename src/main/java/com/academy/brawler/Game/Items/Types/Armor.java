package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.ItemSlot;

import static com.academy.brawler.Game.Items.Fields.ABSORBTION_TAG;
import static com.academy.brawler.Game.Items.Fields.ITEM_SLOTS_TAG;

public class Armor implements Item {

    public Armor(){
        setFields();
        fields.add(new Field<Long>(ABSORBTION_TAG, true));
        getField(ITEM_SLOTS_TAG).addValue(ItemSlot.BODY);
    }

}
