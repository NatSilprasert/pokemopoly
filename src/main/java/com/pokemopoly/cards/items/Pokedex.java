package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.MainGameUI;

public class Pokedex extends ItemCard {
    public Pokedex() {
        super("pokedex", "Pokedex", "Move to crown tile.");
    }

    @Override
    public void activate(Game game, MainGameUI gameUI) {
        Player player = game.getCurrentPlayer();

        System.out.println("🔎 " + player.getName() + " used Pokedex!");

        int targetPos = 39;
        int currentPos = player.getPosition();
        int moveSteps = (targetPos - currentPos + 40) % 40;

        System.out.println("Moving " + player.getName() + " to Daycare (tile " + targetPos + ")");

        gameUI.movePlayerIcon(player, moveSteps, game.getBoard());
    }

    @Override public boolean isAsync() { return true; }
}