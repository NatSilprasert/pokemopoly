package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Bulbasaur extends PokemonCard implements Evolvable {

    public Bulbasaur() {
        super(
                "P001",
                "bulbasaur",
                "Bulbasaur is a Grass/Poison type Pokémon introduced in Generation 1.",
                8,
                5,
                3
        );
    }

    @Override
    public PokemonCard evolve() {
        return new Ivysaur(); //Edit 10/29/68
    }
    //Create 10/23/68
}
