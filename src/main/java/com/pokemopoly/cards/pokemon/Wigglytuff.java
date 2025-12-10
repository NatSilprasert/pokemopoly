package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Wigglytuff extends PokemonCard {
    public Wigglytuff() {
        super("P040",
                "Wigglytuff",
                "Wigglytuff is a Normal type Pokémon introduced in Generation 1.",
                14,
                6,
                4,
                List.of(PokemonType.NORMAL));
    }
}
