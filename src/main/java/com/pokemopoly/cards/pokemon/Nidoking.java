package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Nidoking extends PokemonCard {
    public Nidoking() {
        super("P034",
                "Nidoking",
                "Nidoking is a Poison/Ground type Pokémon introduced in Generation 1.",
                12,
                11,
                6,
                List.of(PokemonType.POISON,PokemonType.GROUND));
    }
}
