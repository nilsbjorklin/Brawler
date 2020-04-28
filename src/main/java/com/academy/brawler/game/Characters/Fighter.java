package com.academy.brawler.game.Characters;

import com.academy.brawler.game.Attributes;
import com.academy.brawler.game.Items.Fields.FieldName;

import java.io.InvalidObjectException;

public class Fighter extends Attributes {
    private String name;
    private Equipment equipment;
    private int maxHealth;
    private int damageTaken;
    private Tactic tactic;

    public Fighter(final String gladiatorName, final Attributes gladiatorAttributes, final Equipment gladiatorEquipment) {
        this.name = gladiatorName;
        this.equipment = gladiatorEquipment;
        this.maxHealth = (int) Math.round(gladiatorAttributes.getHealth() * 0.8 + gladiatorAttributes.getStrength() * 0.4 + gladiatorAttributes.getEndurance() * 0.1);
        this.damageTaken = 0;
    }

    public void setTactic(final Tactic tactic) {
        this.tactic = tactic;
    }

    public String getName() {
        return name;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public int getActionsPerTurn() throws InvalidObjectException {
        return equipment.getActionsPerTurn();
    }

    public int mainhandAttack() throws InvalidObjectException {
        return getEquipment().getMainHand().generateHit(getStrength());
    }

    public int offhandAttack() throws InvalidObjectException {
        return getEquipment().getOffHand().generateHit(getStrength());
    }

    public int getArmorAbsorption() throws InvalidObjectException {
        return getEquipment().getArmor().getSingleField(FieldName.ABSORPTION, Integer.SIZE).getValue();
    }

    public int getCurrentHealth() {
        return maxHealth - damageTaken;
    }

    public void takeDamage(final int damage) {
        damageTaken += damage;
    }

    public String damageText(final int damage) {
        if (damage >= maxHealth * 0.8) {
            return "very hard";
        } else if (damage >= maxHealth * 0.6) {
            return "hard";
        } else if (damage >= maxHealth * 0.4) {
            return "moderately hard";
        } else {
            return "lightly";
        }
    }
}

enum Tactic {
    NORMAL("Normal", 0, 0),
    LIGHT_OFFENSIVE("Aggressive/Light attacks",-1, 1),
    OFFENSIVE("Aggressive",0, 1),
    HEAVY_OFFENSIVE("Aggressive/Heavy attacks",1, 1),
    LIGHT_DEFENSIVE("Defensive/Light attacks",-1, -1),
    DEFENSIVE("Defensive",0, -1),
    HEAVY_DEFENSIVE("Defensive/Heavy attacks",1, -1);
    private static final double MODIFIER = 0.25;
    private String name;
    private double strength = 1;
    private double initiative = 1;
    private double defence = 1;
    private double weapon = 1;

    Tactic(final String displayName, final int heavy, final int offensive) {
        this.name = displayName;
        if (heavy > 0) {
            setHeavy();
        } else if (heavy < 0) {
            setLight();
        }
        if (offensive > 0) {
            setOffensive();
        } else if (offensive < 0) {
            setDefensive();
        }
    }

    private void setHeavy() {
        this.strength += MODIFIER;
        this.weapon -= MODIFIER;
    }

    private void setLight() {
        this.strength -= MODIFIER;
        this.weapon += MODIFIER;
    }

    private void setOffensive() {
        this.initiative += MODIFIER;
        this.defence -= MODIFIER;
    }

    private void setDefensive() {
        this.initiative -= MODIFIER;
        this.defence += MODIFIER;
    }

    public String getName() {
        return name;
    }

    public double getStrength() {
        return strength;
    }

    public double getInitiative() {
        return initiative;
    }

    public double getDefence() {
        return defence;
    }

    public double getWeapon() {
        return weapon;
    }
}
