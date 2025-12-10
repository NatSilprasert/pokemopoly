package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Nidoqueen extends PokemonCard {
    public Nidoqueen() {
        super("P031",
                "Nidoqueen",
                "Nidoqueen is a Poison/Ground type Pokémon introduced in Generation 1.",
                13,
                10,
                6,
                List.of(PokemonType.POISON,PokemonType.GROUND));
    }
}
