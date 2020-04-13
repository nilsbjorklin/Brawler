package com.academy.brawler.Game.Items.Types.Weapons;

import static com.academy.brawler.Game.Items.FieldName.IS_TWO_HANDED;

public class TwoHandWeapon extends Weapon {
    public TwoHandWeapon(){
        setFields();
        addFieldValues(IS_TWO_HANDED, true);
    }
}
