package com.academy.brawler.Game.Items.Types.Weapons;

import com.academy.brawler.Game.Items.Fields.FieldName;
import com.academy.brawler.Game.Items.Fields.SingleField;
import com.academy.brawler.Game.Items.Item;

import java.util.Random;

public abstract class Weapon extends Item {

    public Weapon() {
        setField(FieldName.BREAK_VALUE, new SingleField<Integer>(FieldName.BREAK_VALUE, true));
        setField(FieldName.MIN_DAMAGE, new SingleField<Integer>(FieldName.MIN_DAMAGE, true));
        setField(FieldName.MAX_DAMAGE, new SingleField<Integer>(FieldName.MAX_DAMAGE, true));
        setField(FieldName.DAMAGE_CEILING, new SingleField<Integer>(FieldName.DAMAGE_CEILING, true));
        setField(FieldName.IS_TWO_HANDED, new SingleField<Boolean>(FieldName.IS_TWO_HANDED, true));
        setField(FieldName.WEAPON_TYPE, new SingleField<WeaponType>(FieldName.WEAPON_TYPE, true));
    }

    public int generateHit(final int strength){
       /* int minDamage = (int) getField(MIN_DAMAGE).getValue();
        int maxDamage = (int) getField(MAX_DAMAGE).getValue();
        int damageCeiling = (int) getField(DAMAGE_CEILING).getValue();
        int strengthMultiplier = (strength / 10);
        int generateHit = getRandomNumberInRange(minDamage, maxDamage);
        return Math.min(generateHit + strengthMultiplier, damageCeiling);*/
       return 0;
    }

    private static int getRandomNumberInRange(final int min, final int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
