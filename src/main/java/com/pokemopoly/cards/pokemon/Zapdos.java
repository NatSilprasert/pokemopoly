package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Zapdos extends PokemonCard {
    public Zapdos() {
        super("P145",
                "Zapdos",
                "Zapdos is an Electric/Flying type Pokémon introduced in Generation 1.",
                19,
                19,
                10,
                List.of(PokemonType.ELECTRIC,PokemonType.FLYING));
    }
}