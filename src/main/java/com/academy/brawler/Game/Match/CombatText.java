package com.academy.brawler.Game.Match;

import com.academy.brawler.Game.Characters.Fighter;
import com.academy.brawler.Game.Items.FieldName;
import com.academy.brawler.Game.Items.Item;
import com.academy.brawler.Game.Items.Types.Weapons.Weapon;


public class CombatText {

    static String combatInitiate(final Fighter attacker, final Item weapon, final Fighter defender) {
        return String.format("%s launches at %s with %s,", attacker.getName(), defender.getName(), weapon.getField(FieldName.NAME));
    }

    static String combatDodge(final Fighter defender) {
        return String.format("%s manages to dodge the attack.", defender.getName());
    }

    static String combatParry(final Fighter defender, final Item parryItem) {
        return String.format("%s manages to parry the attack with %s.", defender.getName(), parryItem.getField(FieldName.NAME).getValue());
    }

    static String combatAttack(final Fighter attacker, final Fighter defender, final Weapon weapon) {
        final int damage = weapon.generateHit(attacker.getAttributes().getStrength()) - (int) defender.getEquipment().getArmor().getField(FieldName.ABSORBTION).getValue();
        if (damage <= 0) {
            return String.format("%s failed to dodge or parry the attack and but the hit is absorbed by %s.", defender.getName(), defender.getEquipment().getArmor().getField(FieldName.NAME));
        } else {
            return String.format("%s failed to dodge or parry the attack and is hit %s for %d damage.", defender.getName(), defender.damageText(damage), damage);
        }
    }

    static String combatForfeit(final Fighter defender, final int currentDamageTaken){
        String reason = "chooses to give up";
        if (currentDamageTaken > defender.getAttributes().getHealth() + 15){
            reason = "is killed.";
        } else if (currentDamageTaken > defender.getAttributes().getHealth()){
            reason = "is sent to the infirmary.";
        }
        return String.format("%s has taken to much damage and ", defender.getName());
    }
}
