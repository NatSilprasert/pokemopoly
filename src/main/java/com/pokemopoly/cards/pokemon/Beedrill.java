package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Beedrill extends PokemonCard {
    public Beedrill() {
        super("P015",
                "Beedrill",
                "Beedrill is a Bug/Flying type Pokémon introduced in Generation 1.",
                11,
                10,
                6,
                List.of(PokemonType.BUG,PokemonType.FLYING));
    }
}
