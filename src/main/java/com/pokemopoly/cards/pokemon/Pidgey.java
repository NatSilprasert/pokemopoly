package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Pidgey extends PokemonCard implements Evolvable {
    public Pidgey() {
        super("P016",
                "Pidgey",
                "Pidgey is a Normal/Flying type Pokémon introduced in Generation 1.",
                5,
                3,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Pidgeot(); //Edit 10/29/68
    }
    //Create 10/24/2025
}
