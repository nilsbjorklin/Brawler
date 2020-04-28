package com.academy.brawler.game.Match;

import com.academy.brawler.game.Characters.Fighter;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Match {
    private Team firstTeam;
    private Team secondTeam;
    private List<Fighter> allFighters;

    public Match(final Team first, final Team second) {
        this.firstTeam = first;
        this.secondTeam = second;
        allFighters = new ArrayList<>();
        allFighters.addAll(first.getFighters());
        allFighters.addAll(second.getFighters());
        allFighters.sort(new SortByInitiative());
    }

    public String runMatch() throws InvalidObjectException {
        StringBuilder sb = new StringBuilder();
        sb.append("Team 1: ");
        sb.append(firstTeam.getFighters().stream().map(Fighter::getName).collect(Collectors.joining(", ")));
        sb.append("\nTeam 2: ");
        sb.append(secondTeam.getFighters().stream().map(Fighter::getName).collect(Collectors.joining(", ")));
        sb.append("\n");

        createTurn(sb);
        return sb.toString();
    }

    private void createTurn(final StringBuilder stringBuilder) throws InvalidObjectException {
        for (Fighter fighter : allFighters) {
            stringBuilder.append("\n");
            Fighter target = getOppositeTeam(getFightersTeam(fighter)).getRandomFigther();
            stringBuilder.append(CombatText.combatInitiate(fighter, target, target.getEquipment().getMainHand()));
            stringBuilder.append(CombatText.combatAttack(fighter, target, target.getEquipment().getMainHand()));
        }
    }

    private Team getOppositeTeam(final Team team) {
        if (team.equals(firstTeam))
            return secondTeam;
        if (team.equals(secondTeam))
            return firstTeam;
        throw new NullPointerException("Failed to find opposite team.");
    }

    private Team getFightersTeam(final Fighter fighter) {
        if (firstTeam.hasFighter(fighter)) {
            return firstTeam;
        }
        if (secondTeam.hasFighter(fighter)) {
            return secondTeam;
        }
        throw new NullPointerException("Fighter does not exist in any team.");
    }


    static class SortByInitiative implements Comparator<Fighter> {

        @Override
        public int compare(Fighter first, Fighter second) {
            return second.getInitiative() - first.getInitiative();
        }
    }
}
