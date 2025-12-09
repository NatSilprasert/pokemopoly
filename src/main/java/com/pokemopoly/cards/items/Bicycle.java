package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;

public class Bicycle extends ItemCard {

    public Bicycle() {
        super("bicycle", "Bicycle",
                "Roll Twice.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();

        System.out.println("✨ " + player.getName() + " used Bicycle!");
        System.out.println("➡️ Bicycle effect! Rolled two dice");

        int move1 = game.rollDice();
        game.getBoard().movePlayer(player, move1, game);
        int move2 = game.rollDice();
        game.getBoard().movePlayer(player, move2, game);

    }
}
