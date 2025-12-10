package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.player.Player;

public class GoldenMagikarp extends EventCard {

    public GoldenMagikarp() {
        super("EVT_GOLDEN_MAGIKARP", "Golden Magikarp",
                "You found a Golden Magikarp! Gain +5 coins.");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();
        p.setCoin(p.getCoin() + 5);

        System.out.println("[EVENT] Golden Magikarp! " + p.getName() + " gains +5 coins.");
    }
}