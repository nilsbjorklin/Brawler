package com.academy.brawler.Game.Items.Types;

import com.academy.brawler.Game.Items.Item;

import static com.academy.brawler.Game.Items.Fields.*;

public abstract class Weapon implements Item {

    public Weapon() {
        setFields();
        fields.add(new Field<Long>(BREAK_VALUE_TAG, true));
        fields.add(new Field<Long>(MIN_DAMAGE_TAG, true));
        fields.add(new Field<Long>(MAX_DAMAGE_TAG, true));
        fields.add(new Field<Long>(CEILING_DAMAGE_TAG, true));
        fields.add(new Field<Boolean>(TWO_HANDED_TAG, true));
        fields.add(new Field<Long>(WEAPON_TYPE_TAG, true));
    }
}
