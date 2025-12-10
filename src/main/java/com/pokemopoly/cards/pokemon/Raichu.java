package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Raichu extends PokemonCard {
    public Raichu() {
        super("P026",
                "Raichu",
                "Raichu is an Electric type Pokémon introduced in Generation 1.",
                12,
                12,
                6,
                List.of(PokemonType.ELECTRIC));
    }
}
