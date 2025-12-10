package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Arbok extends PokemonCard {
    public Arbok() {
        super("P024",
                "Arbok",
                "Arbok is a Poison type Pokémon introduced in Generation 1.",
                12,
                7,
                5,
                List.of(PokemonType.POISON));
    }
}
