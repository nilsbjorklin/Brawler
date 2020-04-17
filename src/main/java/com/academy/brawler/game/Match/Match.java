package com.academy.brawler.game.Match;

import com.academy.brawler.game.Characters.Fighter;

import java.util.*;

public class Match {
    private Team firstTeam;
    private Team secondTeam;
    private List<Fighter> allFighters;

    public Match(final Team first, final Team second){
        this.firstTeam = first;
        this.secondTeam = second;
        allFighters.addAll(first.getFighters());
        allFighters.addAll(second.getFighters());
        allFighters.sort(new SortByInitiative());
        allFighters.forEach(fighter -> System.out.printf("Figther %s with %d intiative.", fighter.getName(), fighter.getAttributes().getInitiative()));
    }

    private String CreateTurn(){
        ArrayList<Action> actions = new ArrayList<>();
        return "tesd";//CombatText.combatAttack(firstTeam.getFighters().get(0), secondTeam.getFighters().get(0), firstTeam.getFighters().get(0).getEquipment().getMainHand());
    }


    static class SortByInitiative implements Comparator<Fighter> {

        @Override
        public int compare(Fighter first, Fighter second) {
            return first.getAttributes().getInitiative() - second.getAttributes().getInitiative();
        }
    }
}
