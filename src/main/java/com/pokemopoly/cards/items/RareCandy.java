package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.MainGameUI;

public class RareCandy extends ItemCard {
    public RareCandy() {
        super("rarecandy",
                "Rare Candy",
                "Move to Daycare instant.");
    }

    @Override
    public void activate(Game game, MainGameUI gameUI) {
        Player player = game.getCurrentPlayer();
        System.out.println("✨ " + player.getName() + " used Rare Candy!");

        int currentPos = player.getPosition();
        int targetPos = 18;
        int moveSteps = (targetPos - currentPos + 40) % 40;

        System.out.println("Moving " + player.getName() + " to Daycare (tile " + targetPos + ")");

        gameUI.movePlayerIcon(player, moveSteps, game.getBoard());
    }

    @Override public boolean isAsync() { return true; }
}
