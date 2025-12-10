package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class MrMime extends PokemonCard{

    public MrMime() {
        super("P122",
                "Mr. Mime",
                "Mr. Mime is a Psychic type Pokémon introduced in Generation 1.",
                8,
                9,
                4,
                List.of(PokemonType.PSYCHIC));
    }
}