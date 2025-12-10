package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Persian extends PokemonCard {
    public Persian() {
        super("P053",
                "Persian",
                "Persian is a Normal type Pokémon introduced in Generation 1.",
                11,
                13,
                6,
                List.of(PokemonType.NORMAL));
    }
}
