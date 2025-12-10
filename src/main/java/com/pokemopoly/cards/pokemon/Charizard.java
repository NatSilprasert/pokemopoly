package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Charizard extends PokemonCard {

    public Charizard() {
        super("P006","Charizard",
                "Charizard is a Fire/Flying type Pokémon introduced in Generation 1.",
                14,
                18,
                8,
                List.of(PokemonType.FIRE,PokemonType.FLYING));
    }
}
