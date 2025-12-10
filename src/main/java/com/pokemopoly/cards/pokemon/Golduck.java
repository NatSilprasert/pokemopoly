package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Golduck extends PokemonCard {
    public Golduck() {
        super("P055",
                "Golduck",
                "Golduck is a Water type Pokémon introduced in Generation 1.",
                11,
                12,
                6,
                List.of(PokemonType.WATER));

    }
}
