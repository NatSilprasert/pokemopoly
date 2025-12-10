package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Kakuna extends PokemonCard implements Evolvable {
    public Kakuna() {
        super("P014",
                "Kakuna",
                "Kakuna is a Bug/Poison type Pokémon introduced in Generation 1.",
                10,
                2,
                2,
                List.of(PokemonType.BUG,PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Beedrill();
    }
}
