package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Fearow extends PokemonCard {
    public Fearow() {
        super("P022",
                "Fearow",
                "Fearow is a Normal/Flying type Pokémon introduced in Generation 1.",
                11,
                11,
                6,
                List.of(PokemonType.NORMAL,PokemonType.FLYING));
    }
}
