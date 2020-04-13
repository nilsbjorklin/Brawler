package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.ItemSlot;

import static com.academy.brawler.Game.Items.FieldName.*;

public class Armor implements Item {

    public Armor(){
        setFields();
        fields.add(new Field<Long>(ABSORBTION, true));
        getField(ITEM_SLOTS).addValue(ItemSlot.BODY);
    }

}
