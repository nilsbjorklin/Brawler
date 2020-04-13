package com.academy.brawler.Game.Items.Types.Weapons;

import com.academy.brawler.Game.Attributes;
import com.academy.brawler.Game.Items.FieldName;
import com.academy.brawler.Game.Items.Item;

public class CreateWeapon {
    private Weapon weapon;

    private void weaponConstructor(final WeaponType weaponType, final String weaponName, final String weaponDescription){
        weapon.addFieldValues(FieldName.WEAPON_TYPE, weaponType);
        weapon.addFieldValues(FieldName.NAME, weaponName);
        weapon.addFieldValues(FieldName.DESCRIPTION, weaponDescription);
    }

    private void setOneHandWeapon(){
        weapon = new TwoHandWeapon();
    }

    private void setTwoHandWeapon(){
        weapon = new OneHandWeapon();
    }

    public CreateWeapon CreateDagger(final String weaponName, final String weaponDescription){
        setOneHandWeapon();
        weaponConstructor(WeaponType.DAGGER, weaponName, weaponDescription);
        return this;
    }

    public CreateWeapon weight(final int weaponWeight){
        weapon.addFieldValues(FieldName.WEIGHT, weaponWeight);
        return this;
    }

    public CreateWeapon breakValue(final int weaponBreakValue){
        weapon.addFieldValues(FieldName.BREAK_VALUE, weaponBreakValue);
        return this;
    }

    public CreateWeapon damage(final int minDamage, final int maxDamage, final int damageCeiling){
        weapon.addFieldValues(FieldName.MIN_DAMAGE, minDamage);
        weapon.addFieldValues(FieldName.MAX_DAMAGE, maxDamage);
        weapon.addFieldValues(FieldName.DAMAGE_CEILING, damageCeiling);
        return this;
    }

    public CreateWeapon requirements(final Attributes weaponRequirements){
        weapon.addFieldValues(FieldName.REQUIREMENTS, weaponRequirements);
        return this;
    }

    public CreateWeapon attributes(final Attributes weaponAttributes){
        weapon.addFieldValues(FieldName.ATTRIBUTES, weaponAttributes);
        return this;
    }

    public Weapon build() throws Item.MissingFieldException {
        weapon.checkFields();
        return weapon;
    }
}
