package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Weedle extends PokemonCard implements Evolvable {
    public Weedle() {
        super("P013",
                "Weedle",
                "Weedle is a Bug/Flying type Pokémon introduced in Generation 1.",
                3,
                3,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Kakuna();
    }
    //Create 10/23/68

}
