package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.player.Player;

public class StopThisIsPolice extends EventCard {
    public StopThisIsPolice() {
        super("STOP_POLICE", "Stop! This is Police!",
                "You must skip your next turn.");
    }

    @Override
    public void activate(Game game) {
        Player current = game.getCurrentPlayer();

        System.out.println("🚓 STOP! THIS IS POLICE! 🚓");
        System.out.println(current.getName() + " must skip the next turn!");

        current.setSkipTurn(true);
    }
}
