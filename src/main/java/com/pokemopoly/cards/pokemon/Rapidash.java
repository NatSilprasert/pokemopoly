package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Rapidash extends PokemonCard {
    public Rapidash() {
        super("P078",
                "Rapidash",
                "Rapidash is a Fire type Pokémon introduced in Generation 1.",
                11,
                11,
                6,
                List.of(PokemonType.FIRE));
    }
}
