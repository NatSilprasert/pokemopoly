package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;

import java.util.Scanner;

public class Mother extends EventCard {
    public Mother() {
        super("EVT_MOTHER", "Mother",
                "Heal all Pokémon in their team");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();

        for (PokemonCard mon : p.getTeam()) {
            mon.setHp(mon.getMaxHp());
        }

        System.out.println("[EVENT] Mother healed all Pokémon of " + p.getName() + " ❤️");
    }
}