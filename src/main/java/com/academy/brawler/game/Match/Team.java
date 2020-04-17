package com.academy.brawler.game.Match;

import com.academy.brawler.game.Characters.Fighter;

import java.util.Arrays;
import java.util.List;


public class Team {
    private List<Fighter> fighters;

    public Team(final Fighter ... fightersArr){
        fighters.addAll(Arrays.asList(fightersArr));

    }

    public List<Fighter> getFighters() {
        return fighters;
    }
}
