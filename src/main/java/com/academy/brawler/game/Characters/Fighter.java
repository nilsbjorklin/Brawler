package com.academy.brawler.game.Characters;

import com.academy.brawler.game.Attributes;

import java.io.InvalidObjectException;

public interface Fighter {
    String getName();
    Attributes getAttributes();
    Equipment getEquipment();
    int getActionsPerTurn() throws InvalidObjectException;

    int getCurrentHealth();

    void takeDamage(final int damage);

    default String damageText(final int damage){
        int maxHealth = getAttributes().getHealth();
        if (damage >= maxHealth * 0.8){
            return "very hard";
        } else if (damage >= maxHealth * 0.6){
            return "hard";
        } else if (damage >= maxHealth * 0.4){
            return "moderately hard";
        } else{
            return "lighty";
        }
    }
}
