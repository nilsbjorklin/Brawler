package com.academy.brawler;


import com.academy.brawler.Game.Items.Types.Shield;

public class Main {

    public static void main(String[] args) {
        Shield shield = new Shield();
        System.out.println(shield.asJson().toPrettyString());

    }
}
