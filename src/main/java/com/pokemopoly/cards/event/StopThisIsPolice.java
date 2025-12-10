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
        Player p = game.getCurrentPlayer();
        p.setSkipTurn(true);

        System.out.println("[EVENT] Stop! This is Police!" + p.getName() + " was stopped by police. Next turn skipped!");
    }
}