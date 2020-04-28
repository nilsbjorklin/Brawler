package com.academy.brawler.game.Match;

import com.academy.brawler.game.Characters.Fighter;

import java.io.InvalidObjectException;

public class Combat {

    public static int calculateDamage(final Fighter attacker, final Fighter defender) throws InvalidObjectException {
        return attacker.mainhandAttack() - defender.getArmorAbsorption();
    }
}
