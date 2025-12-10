package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Pidgeot extends PokemonCard {

    public Pidgeot() {
        super("P018",
                "Pidgeot",
                "Pidgeot is a Normal/Flying type Pokémon introduced in Generation 1.",
                14,
                14,
                8,
                List.of(PokemonType.NORMAL,PokemonType.FLYING));
    }
}
