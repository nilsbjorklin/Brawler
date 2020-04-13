package com.academy.brawler.Game.Items.Types.Weapons;

import com.academy.brawler.Game.Items.Item;

import java.util.Random;

import static com.academy.brawler.Game.Items.FieldName.*;


public abstract class Weapon implements Item {

    public Weapon() {
        setFields();
        fields.add(new Field<Integer>(BREAK_VALUE, true));
        fields.add(new Field<Integer>(MIN_DAMAGE, true));
        fields.add(new Field<Integer>(MAX_DAMAGE, true));
        fields.add(new Field<Integer>(DAMAGE_CEILING, true));
        fields.add(new Field<Boolean>(IS_TWO_HANDED, true));
        fields.add(new Field<WeaponType>(WEAPON_TYPE, true));
    }

    public long generateHit(final int strength){
        int minDamage = (int) getField(MIN_DAMAGE).getValue();
        int maxDamage = (int) getField(MAX_DAMAGE).getValue();
        int damageCeiling = (int) getField(DAMAGE_CEILING).getValue();
        int strengthMultiplier = (strength / 10);
        int generateHit = getRandomNumberInRange(minDamage, maxDamage);
        return Math.min(generateHit + strengthMultiplier, damageCeiling);
    }

    private static int getRandomNumberInRange(final int min, final int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
