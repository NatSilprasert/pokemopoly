package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Pinsir extends PokemonCard {
    public Pinsir() {
        super("P127",
                "Pinsir",
                "Pinsir is a Bug type Pokémon introduced in Generation 1.",
                8,
                12,
                5,
                List.of(PokemonType.BUG));
    }
}