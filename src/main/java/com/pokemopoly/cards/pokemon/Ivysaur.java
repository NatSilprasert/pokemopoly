package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Ivysaur extends PokemonCard implements Evolvable {

    public Ivysaur() {
        super(
                "P002",
                "Ivysaur",
                "Ivysaur is a Grass/Poison type Pokémon introduced in Generation 1.",
                12,
                8,
                6
        );
    }

    @Override
    public PokemonCard evolve() {
        return new Venusaur(); //Edit 10/29/68
    }
    //Create 10/23/68
}
