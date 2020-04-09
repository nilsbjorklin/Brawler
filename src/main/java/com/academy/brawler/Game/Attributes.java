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

    Attributes() {
    }

    public Builder builder() {
        return new Builder();
    }

    static class Builder {
        private Attributes attributes;

        Builder() {
            this.attributes = new Attributes();
        }

        public Builder health(final long value){
            attributes.health = value;
            return this;
        }

        public Builder endurance(final long value){
            attributes.endurance = value;
            return this;
        }

        public Builder strength(final long value){
            attributes.strength = value;
            return this;
        }

        public Builder initiative(final long value){
            attributes.initiative = value;
            return this;
        }

        public Builder dodge(final long value){
            attributes.dodge = value;
            return this;
        }

        public Builder sword(final long value){
            attributes.sword = value;
            return this;
        }

        public Builder dagger(final long value){
            attributes.dagger = value;
            return this;
        }

        public Builder axe(final long value){
            attributes.axe = value;
            return this;
        }

        public Builder spear(final long value){
            attributes.spear = value;
            return this;
        }

        public Builder shield(final long value){
            attributes.shield = value;
            return this;
        }

        public Builder mace(final long value){
            attributes.mace = value;
            return this;
        }

        public Attributes build() {
            return attributes;
        }
    }
}
