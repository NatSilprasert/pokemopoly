package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Golbat extends PokemonCard {
    public Golbat() {
        super("P042",
                "Golbat",
                "Golbat is a Poison/Flying type Pokémon introduced in Generation 1.",
                11,
                7,
                5,
                List.of(PokemonType.POISON,PokemonType.FLYING));
    }
}
