package com.academy.brawler.Game.Items.Types.Weapons;

public enum WeaponType {
    SWORD("sword"), DAGGER("dagger"), AXE("axe"), SPEAR("spear"), SHIELD("shield"), MACE("mace");
    private String weaponValue;

    WeaponType(final String value) {
        this.weaponValue = value;
    }
    public String value(){
        return weaponValue;
    }
}
