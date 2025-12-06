package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Cubone extends PokemonCard implements Evolvable {
    public Cubone() {
        super("P104",
                "Cubone",
                "Cubone is a Ground type Pokémon introduced in Generation 1.",
                5,
                4,
                3);
    }

    @Override
    public PokemonCard evolve() {
        return new Marowak();
    }
}