package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Charmeleon extends PokemonCard implements Evolvable {
    public Charmeleon() {
        super("P005","Charmeleon",
                "Charmeleon is a Fire type Pokémon introduced in Generation 1.",
                11,
                11,
                6);
    }

    @Override
    public PokemonCard evolve() {
        return new Charizard();
    }
    //Create 10/23/68
}
