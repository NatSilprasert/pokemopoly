package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Nidorina extends PokemonCard implements Evolvable {
    public Nidorina() {
        super("P030",
                "Nidorina",
                "Nidorina is a Poison type Pokémon introduced in Generation 1.",
                10,
                6,
                4);
    }

    @Override
    public PokemonCard evolve() {
        return new Nidoqueen();
    }
    //Create 10/29/68
}
