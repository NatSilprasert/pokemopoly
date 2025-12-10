package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Lapras extends PokemonCard {

    public Lapras() {
        super(
                "P131",
                "lapras",
                "Lapras is a Water/Ice type Pokémon introduced in Generation 1.",
                16,
                13,
                8,
                List.of(PokemonType.WATER,PokemonType.ICE));

    }
}
