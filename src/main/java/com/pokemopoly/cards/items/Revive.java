package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.MainGameUI;

import java.util.List;

public class Revive extends ItemCard {

    public Revive() {
        super("revive", "Revive",
                "Revives dead Pokemon.");
    }

    @Override
    public void activate(Game game, MainGameUI gameUI) {
        Player player = game.getCurrentPlayer();
        List<PokemonCard> team = player.getTeam();

        if (team.isEmpty()) {
            System.out.println("❌ You have no Pokémon to revive!");
            return;
        }

        boolean revived = false;

        for (PokemonCard card : team) {
            if (card.getHp() <= 0) {
                card.setHp(card.getMaxHp());
                card.setAlive(true);
                revived = true;
                System.out.println("✨ Revived " + card.getName() + " to full health!");
            }
        }

        if (!revived) {
            System.out.println("ℹ️ All Pokémon are already alive.");
        }
    }
}