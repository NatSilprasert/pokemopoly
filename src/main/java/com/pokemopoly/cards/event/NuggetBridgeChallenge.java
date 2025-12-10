package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.player.Player;

public class NuggetBridgeChallenge extends EventCard {
    public NuggetBridgeChallenge() {
        super("E-NUG01", "Nugget Bridge Challenge",
                "You win this challenge, Gain 3 Coins");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();
        p.setCoin(p.getCoin() + 3);
        System.out.println("[EVENT] Nugget Bridge Challenge " + p.getName() + " found a Nugget and earned 3 coins!");
    }
}