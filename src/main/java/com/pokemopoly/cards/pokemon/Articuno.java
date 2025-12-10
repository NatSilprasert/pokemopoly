package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Articuno extends PokemonCard {
    public Articuno() {
        super("P144",
                "Articuno",
                "Articuno is an Ice/Flying type Pokémon introduced in Generation 1.",
                18,
                20,
                10,
                List.of(PokemonType.ICE,PokemonType.FLYING));
    }
}