package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Squirtle extends PokemonCard implements Evolvable {
    public Squirtle() {
        super("P007","Squirtle",
                "Squirtle is a Water type Pokémon introduced in Generation 1.",
                7,
                6,
                3,
                List.of(PokemonType.WATER));
    }

    @Override
    public PokemonCard evolve() {
        return new Wartortle();
    }
}
