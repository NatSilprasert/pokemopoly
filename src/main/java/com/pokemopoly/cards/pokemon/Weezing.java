package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Weezing extends PokemonCard {
    public Weezing() {
        super("P110",
                "Weezing",
                "Weezing is a Poison type Pokémon introduced in Generation 1.",
                10,
                10,
                5,
                List.of(PokemonType.POISON));
    }
}