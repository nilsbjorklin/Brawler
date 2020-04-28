package com.academy.brawler.game.Match;

import com.academy.brawler.game.Characters.Fighter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Team {
    private List<Fighter> fighters;

    public Team(final Fighter... fightersArr) {
        fighters = new ArrayList<>();
        fighters.addAll(Arrays.asList(fightersArr));

    }

    public List<Fighter> getFighters() {
        return fighters;
    }

    public Fighter getRandomFigther() {
        return fighters.get(new Random().nextInt(fighters.size()));
    }

    public boolean hasFighter(final Fighter fighter) {
        for (Fighter currentFighter : fighters) {
            if (currentFighter.getName().equals(fighter.getName())) {
                return true;
            }
        }
        return false;
    }
}
