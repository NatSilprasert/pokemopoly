package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Raticate extends PokemonCard {
    public Raticate() {
        super("P020",
                "Raticate",
                "Raticate is a Normal type Pokémon introduced in Generation 1.",
                8,
                10,
                4,
                List.of(PokemonType.NORMAL));
    }
}
