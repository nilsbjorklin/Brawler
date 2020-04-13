package com.academy.brawler.Game.Characters;

import com.academy.brawler.Game.Attributes;

public interface Fighter {
    String getName();
    Attributes getAttributes();
    Equipment getEquipment();

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
