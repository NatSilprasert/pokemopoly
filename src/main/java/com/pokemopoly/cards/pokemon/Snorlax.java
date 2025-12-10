package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import java.util.List;

public class Snorlax extends PokemonCard {
    public Snorlax() {
        super("P143",
                "Snorlax",
                "Snorlax is a Normal type Pokémon introduced in Generation 1.",
                25,
                10,
                8,
                List.of(PokemonType.NORMAL));
    }
}