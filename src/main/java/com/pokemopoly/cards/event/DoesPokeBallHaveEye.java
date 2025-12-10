package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.player.Player;

public class DoesPokeBallHaveEye extends EventCard {
    public DoesPokeBallHaveEye() {
        super("event_does_pokeball_have_eye",
                "Does a Poké Ball have an eye?",
                "Losing 2 of their Poke Balls.");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();
        int before = p.getRedBall();

        int n = Math.max(0, before - 2);
        p.setRedBall(n);

        System.out.println("[EVENT] Does a Poke Ball have an eye? " + p.getName() + " lost Poké Balls! (" + before + " → " + p.getRedBall() + ")");
    }
}