package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;

public class SuperRepel extends ItemCard {
    public SuperRepel() {
        super("superrepel", "Super Repel",
                "Move forward 4 tiles. You cannot roll dice or catch Pokémon this turn.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();

        System.out.println("✨ " + player.getName() + " used Super Repel!");
        System.out.println("➡️ Move forward 4 tiles and skip dice roll.");

        // ทำให้เทิร์นนี้ไม่สามารถทอยเต๋า/จับโปเกมอน
        player.setDoNothing(true);

        // เดินหน้า 4 ช่องทันที
        int move = 4;
        game.getBoard().movePlayer(player, move, game);
    }
}
