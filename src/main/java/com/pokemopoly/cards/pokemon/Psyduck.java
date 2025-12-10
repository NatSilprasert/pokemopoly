package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Psyduck extends PokemonCard implements Evolvable{
    public Psyduck() {
        super("P054",
                "Psyduck",
                "Psyduck is a Water type Pokémon introduced in Generation 1.",
                6,
                3,
                2,
                List.of(PokemonType.WATER));
    }

    @Override
    public PokemonCard evolve() {
        return new Golduck();
    }
}
