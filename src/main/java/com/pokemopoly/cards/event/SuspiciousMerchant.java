package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.PokemonCard;
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
        Player player = game.getCurrentPlayer();
        System.out.println("🧥 A Suspicious Merchant appears...");

        // Check if the player can pay
        if (player.getCoin() >= 5) {
            System.out.println(player.getName() + " must pay 5 coins to buy a Coin King.");
            player.setCoin(player.getCoin() - 5);

            // Add Coin King item to player (you can adjust based on your item system)
            PokemonCard magikarp = new Magikarp();
            player.addPokemon(magikarp);

            System.out.println("💰 " + player.getName() + " bought a Magikarp!");
        } else {
            System.out.println("❌ " + player.getName() + " does not have enough coins. Nothing happens.");
        }
    }
}
