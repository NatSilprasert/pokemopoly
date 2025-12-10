package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Chansey extends PokemonCard {
    public Chansey() {
        super("P113",
                "Chansey",
                "Chansey is a Normal type Pokémon introduced in Generation 1.",
                35,
                3,
                6,
                List.of(PokemonType.NORMAL));
    }
}