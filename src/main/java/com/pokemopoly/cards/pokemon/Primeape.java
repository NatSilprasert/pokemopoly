package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Primeape extends PokemonCard {
    public Primeape() {
        super("P057",
                "Primeape",
                "Primeape is a Fighting type Pokémon introduced in Generation 1.",
                9,
                9,
                4,
                List.of(PokemonType.FIGHTING));
    }
}
