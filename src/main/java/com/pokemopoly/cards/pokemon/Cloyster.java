package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Cloyster extends PokemonCard {
    public Cloyster() {
        super("P091",
                "Cloyster",
                "Cloyster is a Water/Ice type Pokémon introduced in Generation 1.",
                15,
                9,
                8,
                List.of(PokemonType.WATER,PokemonType.ICE));
    }

}