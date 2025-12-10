package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Ivysaur extends PokemonCard implements Evolvable {

    public Ivysaur() {
        super(
                "P002",
                "Ivysaur",
                "Ivysaur is a Grass/Poison type Pokémon introduced in Generation 1.",
                12,
                8,
                6,
                List.of(PokemonType.GRASS,PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Venusaur();
    }

}
