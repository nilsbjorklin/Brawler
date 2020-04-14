package com.academy.brawler.Game.Items.Types.Weapons;

public enum WeaponType {
    SWORD("sword", true, true), DAGGER("dagger", true, false), AXE("axe", true, true), SPEAR("spear", false, true), SHIELD("shield", true, false), MACE("mace", true, true);
    private String weaponValue;
    private boolean oneHanded;
    private boolean twoHanded;

    WeaponType(final String value, final boolean canBeOneHanded, final boolean canBeTwoHanded) {
        this.weaponValue = value;
        this.oneHanded = canBeOneHanded;
        this.twoHanded = canBeTwoHanded;
    }

    public String value(){
        return weaponValue;
    }

    public boolean isOneHanded() {
        return oneHanded;
    }

    public boolean isTwoHanded() {
        return twoHanded;
    }
}
