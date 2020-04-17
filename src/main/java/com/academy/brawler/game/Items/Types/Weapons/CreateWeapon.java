package com.academy.brawler.game.Items.Types.Weapons;

import com.academy.brawler.game.Attributes;
import com.academy.brawler.game.Items.Fields.Field;

import java.io.InvalidObjectException;

import static com.academy.brawler.game.Items.Fields.Field.checkValueLarger;
import static com.academy.brawler.game.Items.Fields.Field.checkValueNotNegative;
import static com.academy.brawler.game.Items.Fields.FieldName.*;

public class CreateWeapon {
    private Weapon weapon;

    public CreateWeapon generate(final WeaponType weaponType, final String weaponName, final String weaponDescription, final boolean isTwoHanded) {

        if (isTwoHanded) {
            if (!weaponType.isTwoHanded()){
                throw new IllegalArgumentException(String.format("Weapon '%s' is not allowed to be two handed.", weaponType.name()));
            } else {
                setTwoHandWeapon();
            }
        } else {
            if(!weaponType.isOneHanded()){
                throw new IllegalArgumentException(String.format("Weapon '%s' is not allowed to be one handed.", weaponType.name()));
            } else {
                setOneHandWeapon();
            }
        }
        try {
            weapon.getSingleWeaponTypeField(WEAPON_TYPE).setValue(weaponType);
            weapon.getSingleStringField(NAME).setValue(weaponName);
            weapon.getSingleStringField(DESCRIPTION).setValue(weaponDescription);
        } catch (InvalidObjectException e) {
            System.err.println(e.getLocalizedMessage());
            e.printStackTrace();
            return null;
        }

        return this;
    }

    private void setOneHandWeapon() {
        weapon = new OneHandWeapon();
    }

    private void setTwoHandWeapon() {
        weapon = new TwoHandWeapon();
    }

    public CreateWeapon weight(final int weaponWeight) throws IllegalArgumentException, InvalidObjectException {
        Field.checkValueNotNegative("Weapon weight", weaponWeight);
        weapon.getSingleIntegerField(WEIGHT).setValue(weaponWeight);
        return this;
    }

    public CreateWeapon breakValue(final int weaponBreakValue) throws IllegalArgumentException, InvalidObjectException {
        Field.checkValueNotNegative("Weapon break value", weaponBreakValue);
        weapon.getSingleIntegerField(BREAK_VALUE).setValue(weaponBreakValue);
        return this;
    }

    public CreateWeapon damage(final int minDamage, final int maxDamage, final int damageCeiling) throws IllegalArgumentException, InvalidObjectException {
        checkValueNotNegative("Min damage", minDamage);
        checkValueNotNegative("Max damage", maxDamage);
        checkValueNotNegative("Damage ceiling", damageCeiling);
        checkValueLarger("Min damage", minDamage, "Max damage", maxDamage);
        checkValueLarger("Max damage", maxDamage, "Damage ceiling", damageCeiling);

        weapon.getSingleIntegerField(MIN_DAMAGE).setValue(minDamage);
        weapon.getSingleIntegerField(MAX_DAMAGE).setValue(maxDamage);
        weapon.getSingleIntegerField(DAMAGE_CEILING).setValue(damageCeiling);

        return this;
    }

    public CreateWeapon requirements(final Attributes weaponRequirements) throws InvalidObjectException, IllegalArgumentException {
        weaponRequirements.isValid();
        weapon.getSingleAttributesField(REQUIREMENTS).setValue(weaponRequirements);
        return this;
    }

    public CreateWeapon attributes(final Attributes weaponAttributes) throws InvalidObjectException, IllegalArgumentException {
        weaponAttributes.isValid();
        weapon.getSingleAttributesField(ATTRIBUTES).setValue(weaponAttributes);
        return this;
    }

    public Weapon build() {
        weapon.checkFields();
        return weapon;
    }
}
