package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.Random;

public class FightGiovanni extends EventCard {
    public FightGiovanni() {
        super("EV-FIGHT-GIOVANNI", "Fight Giovanni",
                "One of their Pokémon is randomly knocked out.");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();

        if (p.getTeam().isEmpty()) {
            System.out.println("[EVENT] " + p.getName() + " has no Pokémon to knock out.");
            return;
        }

        Random r = new Random();
        PokemonCard target = p.getTeam().get(r.nextInt(p.getTeam().size()));

        target.setHp(0); // faint
        target.setAlive(false);

        System.out.println("[EVENT] Giovanni attacked! " + target.getName() + " has been knocked out.");
    }
}