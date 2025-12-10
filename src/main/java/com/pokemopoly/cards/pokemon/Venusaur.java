package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Venusaur extends PokemonCard{
    public Venusaur() {
        super(
                "P003",
                "Venusaur",
                "Venusaur is a Grass/Poison type Pokémon introduced in Generation 1.",
                16,
                15,
                8,
                List.of(PokemonType.GRASS,PokemonType.POISON));
    }
}
