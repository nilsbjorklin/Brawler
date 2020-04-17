package com.academy.brawler.game.Items.Types.Weapons;

import com.academy.brawler.game.Items.Fields.FieldName;
import com.academy.brawler.game.Items.Item;

import java.util.Random;

public abstract class Weapon extends Item {

    public Weapon() {
        super();
        createField(FieldName.BREAK_VALUE, true, false, Integer.class);
        createField(FieldName.MIN_DAMAGE, true, false, Integer.class);
        createField(FieldName.MAX_DAMAGE, true, false, Integer.class);
        createField(FieldName.DAMAGE_CEILING, true, false, Integer.class);
        createField(FieldName.IS_TWO_HANDED, true, false, Boolean.class);
        createField(FieldName.WEAPON_TYPE, true, false, WeaponType.class);
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
