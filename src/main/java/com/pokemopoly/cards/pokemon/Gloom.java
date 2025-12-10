package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Gloom extends PokemonCard implements Evolvable {
    public Gloom() {
        super("P044",
                "Gloom",
                "Gloom is a Grass/Poison type Pokémon introduced in Generation 1.",
                8,
                5,
                4,
                List.of(PokemonType.GRASS,PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Vileplume();
    }
}
