package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.pokemon.Magikarp;
import com.pokemopoly.player.Player;

public class SuspiciousMerchant extends EventCard {

    public SuspiciousMerchant() {
        super("EVT-SUSPICIOUS-MERCHANT",
                "Suspicious Merchant",
                "You must buy a Magikarp for 5 coins if you can afford it.");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();

        if (p.getCoin() < 5 || p.isTeamFull()) {
            System.out.println("[EVENT] Suspicious Merchant " + p.getName() + " can't afford the merchant. Nothing happens.");
            return;
        }

        p.setCoin(p.getCoin() - 5);

        p.addPokemon(new Magikarp());

        System.out.println("[EVENT] Suspicious Merchant " + p.getName() + " bought a mysterious item for 5 coins (Bicycle).");
    }
}