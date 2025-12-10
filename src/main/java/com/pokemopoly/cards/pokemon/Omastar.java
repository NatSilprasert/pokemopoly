package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Omastar extends PokemonCard {
    public Omastar() {
        super("P139",
                "Omastar",
                "Omastar is a Rock/Water type Pokémon introduced in Generation 1.",
                15,
                7,
                7,
                List.of(PokemonType.ROCK,PokemonType.WATER));
    }
}