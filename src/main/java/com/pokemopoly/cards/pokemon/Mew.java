package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Mew extends PokemonCard {
    public Mew() {
        super("P151",
                "Mew",
                "Mew is a Psychic type Pokémon introduced in Generation 1.",
                15,
                15,
                10,
                List.of(PokemonType.PSYCHIC));
    }
}