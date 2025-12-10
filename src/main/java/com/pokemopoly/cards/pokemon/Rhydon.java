package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Rhydon extends PokemonCard {
    public Rhydon() {
        super("P112",
                "Rhyhorn",
                "Rhyhorn is a Ground/Rock type Pokémon introduced in Generation 1.",
                14,
                14,
                8,
                List.of(PokemonType.GROUND,PokemonType.ROCK));
    }
}