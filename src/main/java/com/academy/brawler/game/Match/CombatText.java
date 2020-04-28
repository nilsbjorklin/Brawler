package com.academy.brawler.game.Match;


import com.academy.brawler.game.Characters.Fighter;
import com.academy.brawler.game.Items.Fields.FieldName;
import com.academy.brawler.game.Items.Item;
import com.academy.brawler.game.Items.Types.Weapons.WeaponOrShield;

import java.io.InvalidObjectException;

public class CombatText {

    static String combatInitiate(final Fighter attacker, final Fighter defender, final Item weapon) throws InvalidObjectException {
        return String.format("%s launches at %s with %s,", attacker.getName(), defender.getName(), weapon.getSingleField(FieldName.NAME, "").getValue());
    }

    static String combatDodge(final Fighter defender) {
        return String.format("%s manages to dodge the attack.", defender.getName());
    }

    static String combatParry(final Fighter defender, final Item parryItem) throws InvalidObjectException {
        return String.format("%s manages to parry the attack with %s.", defender.getName(), parryItem.getSingleField(FieldName.NAME, "").getValue());
    }

    static String combatAttack(final Fighter attacker, final Fighter defender, final WeaponOrShield weapon) throws InvalidObjectException {
        int damage = 15;
        if (damage <= 0) {
            return String.format("%s failed to dodge or parry the attack and but the hit is absorbed by %s.", defender.getName(), defender.getEquipment().getArmor().getSingleField(FieldName.NAME, "").getValue());
        } else {
            return String.format("%s failed to dodge or parry the attack and is hit %s for %d damage.", defender.getName(), defender.damageText(damage), damage);
        }
    }

    static String combatForfeit(final Fighter defender, final int currentDamageTaken) {
        String reason = "chooses to give up";
        if (currentDamageTaken > defender.getHealth() + 15) {
            reason = "is killed.";
        } else if (currentDamageTaken > defender.getHealth()) {
            reason = "is sent to the infirmary.";
        }
        return String.format("%s has taken to much damage and ", defender.getName());
    }
}
