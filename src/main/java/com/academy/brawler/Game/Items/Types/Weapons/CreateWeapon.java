package com.academy.brawler.Game.Items.Types.Weapons;

import com.academy.brawler.Game.Attributes;
import com.academy.brawler.Game.Items.Fields.SingleField;

import static com.academy.brawler.Game.Items.Fields.FieldName.*;

public class CreateWeapon {
    private Weapon weapon;

    private void weaponConstructor(final WeaponType weaponType, final String weaponName, final String weaponDescription) {
        SingleField<WeaponType> weaponTypeField = (SingleField<WeaponType>) weapon.getField(WEAPON_TYPE);
        weaponTypeField.setValue(weaponType);

        SingleField<String> nameField = (SingleField<String>) weapon.getField(NAME);
        nameField.setValue(weaponName);

        SingleField<String> descriptionField = (SingleField<String>) weapon.getField(DESCRIPTION);
        descriptionField.setValue(weaponDescription);
    }

    private void setOneHandWeapon() {
        weapon = new OneHandWeapon();
    }

    private void setTwoHandWeapon() {
        weapon = new TwoHandWeapon();
    }

    public CreateWeapon CreateDagger(final String weaponName, final String weaponDescription) {
        setOneHandWeapon();
        weaponConstructor(WeaponType.DAGGER, weaponName, weaponDescription);
        return this;
    }

    public CreateWeapon weight(final int weaponWeight) {
        SingleField<Integer> field = (SingleField<Integer>) weapon.getField(WEIGHT);
        field.setValue(weaponWeight);
        return this;
    }

    public CreateWeapon breakValue(final int weaponBreakValue) {
        SingleField<Integer> field = (SingleField<Integer>) weapon.getField(BREAK_VALUE);
        field.setValue(weaponBreakValue);
        return this;
    }

    public CreateWeapon damage(final int minDamage, final int maxDamage, final int damageCeiling) {
        SingleField<Integer> minDamageField = (SingleField<Integer>) weapon.getField(MIN_DAMAGE);
        minDamageField.setValue(minDamage);
        SingleField<Integer> maxDamageField = (SingleField<Integer>) weapon.getField(MAX_DAMAGE);
        maxDamageField.setValue(maxDamage);
        SingleField<Integer> damageCeilingField = (SingleField<Integer>) weapon.getField(DAMAGE_CEILING);
        damageCeilingField.setValue(damageCeiling);

        return this;
    }

    public CreateWeapon requirements(final Attributes weaponRequirements) {
        SingleField<Attributes> attributes = (SingleField<Attributes>) weapon.getField(REQUIREMENTS);
        attributes.setValue(weaponRequirements);
        return this;
    }

    public CreateWeapon attributes(final Attributes weaponAttributes) {
        SingleField<Attributes> attributes = (SingleField<Attributes>) weapon.getField(ATTRIBUTES);
        attributes.setValue(weaponAttributes);
        return this;
    }

    public Weapon build() {
        weapon.checkFields();
        return weapon;
    }
}
