package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Machamp extends PokemonCard{
    public Machamp() {
        super("P068",
                "Machamp",
                "Machamp is a Fighting type Pokémon introduced in Generation 1.",
                13,
                16,
                8,
                List.of(PokemonType.FIGHTING));
    }
}
