package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Clefairy extends PokemonCard implements Evolvable {
    public Clefairy() {
        super("P035",
                "Clefairy",
                "Clefairy is a Normal type Pokémon introduced in Generation 1.",
                5,
                2,
                2,
                List.of(PokemonType.NORMAL));
    }

    @Override
    public PokemonCard evolve() {
        return new Clefable();
    }
}
