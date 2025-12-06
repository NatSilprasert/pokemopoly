package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;

public class MaxRepel extends ItemCard {

    public MaxRepel() {
        super("I020", "Max Repel",
                "Move forward 6 tiles. You cannot roll dice or catch Pokémon this turn.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();

        System.out.println("✨ " + player.getName() + " used Max Repel!");
        System.out.println("➡️ Move forward 6 tiles and skip dice roll.");

        // ทำให้เทิร์นนี้ไม่สามารถทอยเต๋า/จับโปเกมอน
        player.setDoNothing(true);

        // เดินหน้า 6 ช่องทันที
        int move = 6;
        game.getBoard().movePlayer(player, move, game);
    }
}