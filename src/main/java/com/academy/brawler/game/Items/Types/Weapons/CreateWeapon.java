package com.academy.brawler.game.Items.Types.Weapons;

import com.academy.brawler.game.Attributes;
import com.academy.brawler.game.Items.Fields.Field;

import java.io.InvalidObjectException;

import static com.academy.brawler.game.Items.Fields.Field.checkValueLarger;
import static com.academy.brawler.game.Items.Fields.Field.checkValueNotNegative;
import static com.academy.brawler.game.Items.Fields.FieldName.*;

public class CreateWeapon {
    private WeaponOrShield weapon;

    public CreateWeapon generate(final WeaponType weaponType, final String weaponName, final String weaponDescription, final boolean isTwoHanded) throws InvalidObjectException {

        if (isTwoHanded) {
            weapon = WeaponOrShield.twoHandWeapon(weaponType, weaponName, weaponDescription);
        } else {
            weapon = WeaponOrShield.oneHandWeapon(weaponType, weaponName, weaponDescription);
        }

        return this;
    }

    public CreateWeapon weight(final int weaponWeight) throws IllegalArgumentException, InvalidObjectException {
        Field.checkValueNotNegative("Weapon weight", weaponWeight);
        weapon.setSingleField(WEIGHT, weaponWeight);
        return this;
    }

    public CreateWeapon breakValue(final int weaponBreakValue) throws IllegalArgumentException, InvalidObjectException {
        Field.checkValueNotNegative("Weapon break value", weaponBreakValue);
        weapon.setSingleField(BREAK_VALUE, weaponBreakValue);
        return this;
    }

    public CreateWeapon actions(final int actions) throws IllegalArgumentException, InvalidObjectException {
        Field.checkValueNotNegative("Actions", actions);
        weapon.setSingleField(ACTIONS, actions);
        return this;
    }

    public CreateWeapon damage(final int minDamage, final int maxDamage, final int damageCeiling) throws IllegalArgumentException, InvalidObjectException {
        checkValueNotNegative("Min damage", minDamage);
        checkValueNotNegative("Max damage", maxDamage);
        checkValueNotNegative("Damage ceiling", damageCeiling);
        checkValueLarger("Min damage", minDamage, "Max damage", maxDamage);
        checkValueLarger("Max damage", maxDamage, "Damage ceiling", damageCeiling);

        weapon.setSingleField(MIN_DAMAGE, 0);
        weapon.setSingleField(MAX_DAMAGE, 0);
        weapon.setSingleField(DAMAGE_CEILING, 0);

        return this;
    }

    public CreateWeapon requirements(final Attributes weaponRequirements) throws InvalidObjectException, IllegalArgumentException {
        weaponRequirements.isValid();
        weapon.setSingleField(REQUIREMENTS, weaponRequirements);
        return this;
    }

    public CreateWeapon attributes(final Attributes weaponAttributes) throws InvalidObjectException, IllegalArgumentException {
        weaponAttributes.isValid();
        weapon.setSingleField(ATTRIBUTES, weaponAttributes);
        return this;
    }

    public WeaponOrShield build() {
        weapon.checkFields();
        return weapon;
    }
}
