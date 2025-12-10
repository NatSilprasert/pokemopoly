package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Ditto extends PokemonCard {

    public Ditto() {
        super("P132",
                "Ditto",
                "Ditto is a Normal type Pokémon introduced in Generation 1.",
                1,
                1,
                3,
                List.of(PokemonType.NORMAL));
    }
}