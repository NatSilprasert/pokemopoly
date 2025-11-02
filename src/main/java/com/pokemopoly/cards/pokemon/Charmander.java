package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Charmander extends PokemonCard implements Evolvable {
    public Charmander() {
        super("P004","Charmander",
                "Charmander is a Fire type Pokémon introduced in Generation 1.",
                6,
                7,
                3);
    }

    @Override
    public PokemonCard evolve() {
        return new Charmeleon();
    }
    //Create 10/23/68
}
