package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;
import java.util.Scanner;

public class EXPShare extends ItemCard {
    public EXPShare() {
        super("I017",
                "EXP. Share",
                "All Pokémon evolve!");
    }

    @Override
    public void activate(Game game) {

        List<PokemonCard> team = game.getCurrentPlayer().getTeam();

        System.out.println("🔄 EXP Share activated!");
        System.out.println("All eligible Pokémon will evolve!");

        boolean evolvedAny = false;

        for (int i = 0; i < team.size(); i++) {
            PokemonCard p = team.get(i);

            if (p instanceof Evolvable evolvable) {
                PokemonCard evolvedForm = evolvable.evolve();

                if (evolvedForm != null) {
                    System.out.println("✨ " + p.getName() + " evolved into " + evolvedForm.getName() + "!");
                    team.set(i, evolvedForm);
                    evolvedAny = true;
                }
            }
        }

        if (!evolvedAny) {
            System.out.println("❌ No Pokémon in your team can evolve.");
        }
    }
}
