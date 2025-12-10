package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.ui.MainGameUI;

public class HM02Fly extends ItemCard {
    public HM02Fly() {
        super("hm02fly", "HM02 Fly",
                "Move forward 10 tiles.");
    }

    @Override
    public void activate(Game game, MainGameUI gameUI) {
        gameUI.movePlayerIcon(game.getCurrentPlayer(), 10, game.getBoard());
    }

    @Override public boolean isAsync() { return true; }
}
