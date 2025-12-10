package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Onix extends PokemonCard {
    public Onix() {
        super("P095",
                "Onix",
                "Onix is a Rock/Ground type Pokémon introduced in Generation 1.",
                12,
                14,
                8,
                List.of(PokemonType.ROCK,PokemonType.GROUND));
    }
}