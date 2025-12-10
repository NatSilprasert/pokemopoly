package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Hypno extends PokemonCard {
    public Hypno() {
        super("P097",
                "Hypno",
                "Hypno is a Psychic type Pokémon introduced in Generation 1.",
                9,
                14,
                6,
                List.of(PokemonType.PSYCHIC));
    }
}