package com.academy.brawler.Game.Match;

import com.academy.brawler.Game.Characters.Fighter;
import com.academy.brawler.Game.Items.FieldName;
import com.academy.brawler.Game.Items.Item;


public class CombatText {

    private String combatDodge(final Fighter fighter){
        return String.format("%s manages to dodge the attack.", fighter.getName());
    }

    private String combatParry(final Fighter fighter, final Item parryItem){
        return String.format("%s manages to parry the attack with %s.", fighter.getName(), parryItem. getField(FieldName.NAME).getValues().get(0));
    }
}
