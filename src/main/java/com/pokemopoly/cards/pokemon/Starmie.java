package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Starmie extends PokemonCard {
    public Starmie() {
        super("P121",
                "Starmie",
                "Starmie is a Water/Psychic type Pokémon introduced in Generation 1.",
                12,
                12,
                6,
                List.of(PokemonType.WATER,PokemonType.PSYCHIC));
    }
}