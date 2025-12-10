package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Omanyte extends PokemonCard implements Evolvable {
    public Omanyte() {
        super("P138",
                "Omanyte",
                "Omanyte is a Rock/Water type Pokémon introduced in Generation 1.",
                10,
                3,
                4,
                List.of(PokemonType.ROCK,PokemonType.WATER));
    }

    @Override
    public PokemonCard evolve() {
        return new Omastar();
    }
}