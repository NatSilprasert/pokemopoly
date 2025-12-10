package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Poliwrath extends PokemonCard {
    public Poliwrath() {
        super("P062",
                "Poliwrath",
                "Poliwrath is a Water/Fighting type Pokémon introduced in Generation 1.",
                12,
                12,
                6,
                List.of(PokemonType.WATER,PokemonType.FIGHTING));
    }
}
