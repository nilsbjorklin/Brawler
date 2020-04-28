package com.academy.brawler.game.Items.Types.Weapons;

import com.academy.brawler.game.Items.Item;
import com.academy.brawler.game.Items.ItemSlot;

import java.io.InvalidObjectException;
import java.util.Random;

import static com.academy.brawler.game.Items.Fields.FieldName.*;

public class WeaponOrShield extends Item {
    WeaponOrShield(final String weaponName, final String weaponDescription) throws InvalidObjectException {
        super();
        createSingleField(WEAPON_TYPE, true, WeaponType.FIST);
        createSingleField(BREAK_VALUE, true, 0);
        createSingleField(ACTIONS, true, 0);

        setSingleField(NAME, weaponName);
        setSingleField(DESCRIPTION, weaponDescription);
    }

    public static WeaponOrShield shield(final String shieldName, final String shieldDescription) throws InvalidObjectException {
        WeaponOrShield shield = new WeaponOrShield(shieldName, shieldDescription);
        shield.createSingleField(ABSORBTION, true, 0);
        shield.createSingleField(BREAK_VALUE, true, 0);

        shield.addArrayField(ITEM_SLOTS, ItemSlot.OFF_HAND);
        shield.setSingleField(WEAPON_TYPE, WeaponType.SHIELD);

        return shield;
    }

    private static WeaponOrShield weapon(final String weaponName, final String weaponDescription) throws InvalidObjectException {
        WeaponOrShield weapon = new WeaponOrShield(weaponName, weaponDescription);
        weapon.createSingleField(IS_TWO_HANDED, true, true);
        weapon.createSingleField(MIN_DAMAGE, true, 0);
        weapon.createSingleField(MAX_DAMAGE, true, 0);
        weapon.createSingleField(DAMAGE_CEILING, true, 0);
        return weapon;
    }

    public static WeaponOrShield oneHandWeapon(final WeaponType weaponType, final String weaponName, final String weaponDescription) throws InvalidObjectException {
        if (!weaponType.isOneHanded()) {
            throw new IllegalArgumentException(String.format("Cannot create one handed weapon for %s", weaponType.value()));
        }
        WeaponOrShield oneHandWeapon = weapon(weaponName, weaponDescription);
        oneHandWeapon.setSingleField(IS_TWO_HANDED, false);
        oneHandWeapon.setSingleField(WEAPON_TYPE, weaponType);

        oneHandWeapon.addArrayField(ITEM_SLOTS, ItemSlot.OFF_HAND);
        oneHandWeapon.addArrayField(ITEM_SLOTS, ItemSlot.MAIN_HAND);

        return oneHandWeapon;
    }

    public static WeaponOrShield twoHandWeapon(final WeaponType weaponType, final String weaponName, final String weaponDescription) throws InvalidObjectException {
        if (!weaponType.isTwoHanded()) {
            throw new IllegalArgumentException(String.format("Cannot create two handed weapon for %s", weaponType.value()));
        }
        WeaponOrShield twoHandWeapon = weapon(weaponName, weaponDescription);
        twoHandWeapon.setSingleField(IS_TWO_HANDED, true);
        twoHandWeapon.setSingleField(WEAPON_TYPE, weaponType);

        twoHandWeapon.addArrayField(ITEM_SLOTS, ItemSlot.OFF_HAND);

        return twoHandWeapon;
    }


    private static int getRandomNumberInRange(final int min, final int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public int generateHit(final int strength) throws InvalidObjectException {
        int minDamage = getSingleField(MIN_DAMAGE, 0).getValue();
        int maxDamage = getSingleField(MAX_DAMAGE, 0).getValue();
        int damageCeiling = getSingleField(DAMAGE_CEILING, 0).getValue();

        int strengthMultiplier = (strength / 10);
        int generateHit = getRandomNumberInRange(minDamage, maxDamage);
        return Math.min(generateHit + strengthMultiplier, damageCeiling);
    }

}
