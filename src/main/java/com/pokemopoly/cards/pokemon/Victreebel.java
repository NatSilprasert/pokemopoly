package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Victreebel extends PokemonCard {

    public Victreebel() {
        super("P071",
                "Victreebel",
                "Victreebel is a Grass/Poison type Pokémon introduced in Generation 1.",
                14,
                10,
                7,
                List.of(PokemonType.GRASS,PokemonType.POISON));
    }
}
