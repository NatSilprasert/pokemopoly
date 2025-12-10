package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Clefable extends PokemonCard {
    public Clefable() {
        super("P036",
                "Clefable",
                "Clefable is a Normal type Pokémon introduced in Generation 1.",
                10,
                6,
                4,
                List.of(PokemonType.NORMAL));
    }
}
