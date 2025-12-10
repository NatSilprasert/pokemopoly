package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Caterpie extends PokemonCard implements Evolvable {
    public Caterpie() {
        super("P010",
                "Caterpie",
                "Caterpie is a Bug type Pokémon introduced in Generation 1.",
                3,
                3,
                2,
                List.of(PokemonType.BUG));
    }

    @Override
    public PokemonCard evolve() {
        return new Metapod();
    }
}
