package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class FarfetchD extends PokemonCard {
    public FarfetchD() {
        super("P083",
                "Farfetch'd",
                "Farfetch'd is a Normal/Flying type Pokémon introduced in Generation 1.",
                5,
                8,
                4,
                List.of(PokemonType.NORMAL,PokemonType.FLYING));
    }
}