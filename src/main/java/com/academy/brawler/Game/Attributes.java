package com.academy.brawler.Game;

public class Attributes {
    private long health = 0;
    private long endurance = 0;
    private long strength = 0;
    private long initiative = 0;
    private long dodge = 0;
    private long sword = 0;
    private long dagger = 0;
    private long axe = 0;
    private long spear = 0;
    private long shield = 0;
    private long mace = 0;

    public long getHealth() {
        return health;
    }

    public long getEndurance() {
        return endurance;
    }

    public long getStrength() {
        return strength;
    }

    public long getInitiative() {
        return initiative;
    }

    public long getDodge() {
        return dodge;
    }

    public long getSword() {
        return sword;
    }

    public long getDagger() {
        return dagger;
    }

    public long getAxe() {
        return axe;
    }

    public long getSpear() {
        return spear;
    }

    public long getShield() {
        return shield;
    }

    public long getMace() {
        return mace;
    }

    public Attributes health(final long value) {
        this.health = value;
        return this;
    }

    public Attributes endurance(final long value) {
        this.endurance = value;
        return this;
    }

    public Attributes strength(final long value) {
        this.strength = value;
        return this;
    }

    public Attributes initiative(final long value) {
        this.initiative = value;
        return this;
    }

    public Attributes dodge(final long value) {
        this.dodge = value;
        return this;
    }

    public Attributes sword(final long value) {
        this.sword = value;
        return this;
    }

    public Attributes dagger(final long value) {
        this.dagger = value;
        return this;
    }

    public Attributes axe(final long value) {
        this.axe = value;
        return this;
    }

    public Attributes spear(final long value) {
        this.spear = value;
        return this;
    }

    public Attributes shield(final long value) {
        this.shield = value;
        return this;
    }

    public Attributes mace(final long value) {
        this.mace = value;
        return this;
    }

    public Attributes() {
    }

}