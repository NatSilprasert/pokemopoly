package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.MainGameUI;

public class EjectButton extends ItemCard {
    public EjectButton() {
        super("eject_button", "Eject Button",
                "Move backward 1 tile");
    }

    @Override
    public void activate(Game game, MainGameUI gameUI) {
        Player player = game.getCurrentPlayer();
        int currentPos = player.getPosition();
        int boardSize = 40;

        int targetPos = (currentPos - 1 + boardSize) % boardSize;
        int moveSteps = (targetPos - currentPos + boardSize) % boardSize;

        System.out.println("✨ " + player.getName() + " used Eject Button!");
        System.out.println("Moving backward from " + currentPos + " → " + targetPos);

        gameUI.movePlayerIcon(player, moveSteps, game.getBoard());
    }

    @Override public boolean isAsync() { return true; }
}
