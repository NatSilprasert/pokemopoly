package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Moltres extends PokemonCard {

    public Moltres() {
        super("P146",
                "Moltres",
                "Moltres is a Fire/Flying type Pokémon introduced in Generation 1.",
                20,
                18,
                10,
                List.of(PokemonType.FIRE,PokemonType.FLYING));
    }
}