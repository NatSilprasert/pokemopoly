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
        Player player = game.getCurrentPlayer();
        if (player == null) {
            System.out.println("Error: No current player found.");
            return;
        }

        // Add coins
        player.setCoin(player.getCoin() + 5);

        System.out.println("✨ " + player.getName()
                + " found a Golden Magikarp and gained +5 coins!");
    }
}
