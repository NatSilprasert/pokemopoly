package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Metapod extends PokemonCard implements Evolvable {
    public Metapod() {
        super("P011",
                "Metapod",
                "Metapod is a Bug type Pokémon introduced in Generation 1.",
                10,
                2,
                2,
                List.of(PokemonType.BUG));
    }

    @Override
    public PokemonCard evolve() {
        return new Butterfree();
    }
}
