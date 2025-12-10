package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Magneton extends PokemonCard {
    public Magneton() {
        super("P082",
                "Magneton",
                "Magneton is an Electric type Pokémon introduced in Generation 1.",
                10,
                10,
                6,
                List.of(PokemonType.ELECTRIC));
    }
}