package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Electrode extends PokemonCard {
    public Electrode() {
        super("P101",
                "Electrode",
                "Electrode is an Electric type Pokémon introduced in Generation 1.",
                12,
                12,
                6,
                List.of(PokemonType.ELECTRIC));
    }
}