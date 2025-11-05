package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Paras extends PokemonCard implements Evolvable {
    public Paras() {
        super("P046",
                "Paras",
                "Paras is a Bug/Grass type Pokémon introduced in Generation 1.",
                4,
                2,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Parasect();
    }
    //Create 11/2/68
}
