package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Dugtrio extends PokemonCard{
    public Dugtrio() {
        super("P051",
                "Dugtrio",
                "Dugtrio is a Ground type Pokémon introduced in Generation 1.",
                10,
                14,
                6,
                List.of(PokemonType.GROUND));
    }
}
