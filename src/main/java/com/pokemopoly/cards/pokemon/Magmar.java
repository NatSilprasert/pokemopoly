package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Magmar extends PokemonCard {
    public Magmar() {
        super("P126",
                "Magmar",
                "Magmar is a Fire type Pokémon introduced in Generation 1.",
                12,
                12,
                7,
                List.of(PokemonType.FIRE));
    }
}