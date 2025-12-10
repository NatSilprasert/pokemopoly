package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Dodrio extends PokemonCard {
    public Dodrio() {
        super("P085",
                "Dodrio",
                "Dodrio is a Normal/Flying type Pokémon introduced in Generation 1.",
                10,
                11,
                6,
                List.of(PokemonType.NORMAL,PokemonType.FLYING));
    }
}