package com.academy.brawler.Game.Items.Types;

import static com.academy.brawler.Game.Items.Fields.TWO_HANDED_TAG;

public class OneHandWeapon extends Weapon {
    public OneHandWeapon(){
        setFieldValue(TWO_HANDED_TAG, false);
    }
}
