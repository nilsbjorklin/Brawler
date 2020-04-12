package com.academy.brawler.Game.Items.Types;

import static com.academy.brawler.Game.Items.Fields.TWO_HANDED_TAG;

public class TwoHandWeapon extends Weapon {
    public TwoHandWeapon(){
        setFieldValue(TWO_HANDED_TAG, true);
    }
}
