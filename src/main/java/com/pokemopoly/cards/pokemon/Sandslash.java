package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Sandslash extends PokemonCard {
    public Sandslash() {
        super("P028",
                "Sandslash",
                "Sandslash is a Ground type Pokémon introduced in Generation 1.",
                10,
                7,
                5,
                List.of(PokemonType.GROUND));
    }
}
