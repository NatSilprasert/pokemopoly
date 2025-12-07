package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.List;

public class TrueRocket extends EventCard {
    public TrueRocket() {
        super("E-TR01", "True Rocket",
                "If you ask who we are... A random Pokémon in your team disappears (If you have only 1 Pokémon, nothing happens).");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        List<PokemonCard> team = player.getTeam();

        System.out.println("🚀 Team Rocket appears! \"If you ask who we are...\"");

        // If team size <= 1 → no effect
        if (team.size() <= 1) {
            System.out.println("Nothing happens because you only have one Pokémon.");
            return;
        }

        // Randomly choose 1 Pokémon to remove
        int randomIndex = (int) (Math.random() * team.size());
        PokemonCard removed = team.get(randomIndex);

        // Remove the Pokémon
        team.remove(removed);

        System.out.println("💥 Team Rocket stole your Pokémon: " + removed.getName() + "!");
    }
}
