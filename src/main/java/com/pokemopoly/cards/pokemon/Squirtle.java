package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Squirtle extends PokemonCard implements Evolvable {
    public Squirtle() {
        super("P007","Squirtle",
                "Squirtle is a Water type Pokémon introduced in Generation 1.",
                7,
                6,
                3);
    }

    @Override
    public PokemonCard evolve() {
        return new Wartortle();
    }
    //Create 10/23/68
}
