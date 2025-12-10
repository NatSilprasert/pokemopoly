package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Zubat extends PokemonCard implements Evolvable {
    public Zubat() {
        super("P041",
                "Zubat",
                "Zubat is a Poison/Flying type Pokémon introduced in Generation 1.",
                5,
                3,
                2,
                List.of(PokemonType.POISON,PokemonType.FLYING));
    }

    @Override
    public PokemonCard evolve() {
        return new Golbat();
    }
}
