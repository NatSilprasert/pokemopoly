package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Vileplume extends PokemonCard{
    public Vileplume() {
        super("P045",
                "Vileplume",
                "Vileplume is a Grass/Poison type Pokémon introduced in Generation 1.",
                14,
                8,
                7,
                List.of(PokemonType.GRASS,PokemonType.POISON));
    }
}
