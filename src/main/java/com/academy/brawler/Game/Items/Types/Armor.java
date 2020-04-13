package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Fields.ArrayField;
import com.academy.brawler.Game.Items.Fields.FieldName;
import com.academy.brawler.Game.Items.Fields.SingleField;
import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.ItemSlot;

import static com.academy.brawler.Game.Items.Fields.FieldName.*;

public class Armor extends Item {

    public Armor(){
        super();
        setField(FieldName.ABSORBTION, new SingleField<Integer>(FieldName.ABSORBTION, true));

        ArrayField<ItemSlot> itemSlots = (ArrayField<ItemSlot>)getField(ITEM_SLOTS);
        itemSlots.addValue(ItemSlot.BODY);
    }

}
