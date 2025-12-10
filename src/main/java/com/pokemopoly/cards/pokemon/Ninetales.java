package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Ninetales extends PokemonCard {
    public Ninetales() {
        super("P038",
                "Ninetales",
                "Ninetales is a Fire type Pokémon introduced in Generation 1.",
                12,
                12,
                6,
                List.of(PokemonType.FIRE));
    }
}
