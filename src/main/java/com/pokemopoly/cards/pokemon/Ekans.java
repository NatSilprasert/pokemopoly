package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Ekans extends PokemonCard implements Evolvable {
    public Ekans() {
        super("P023",
                "Ekans",
                "Ekans is a Poison type Pokémon introduced in Generation 1.",
                4,
                5,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Arbok();
    }
    //Create 10/29/68
}
