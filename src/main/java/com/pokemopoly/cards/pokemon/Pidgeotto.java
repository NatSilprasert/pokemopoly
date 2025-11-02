package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Pidgeotto extends PokemonCard implements Evolvable {
    public Pidgeotto() {
        super("P017",
                "Pidgeotto",
                "Pidgeotto is a Normal/Flying type Pokémon introduced in Generation 1.",
                12,
                6,
                4);
    }

    @Override
    public PokemonCard evolve() {
        return new Pidgeot(); //Edit 10/29/68
    }
    //Create 10/24/2025
}
