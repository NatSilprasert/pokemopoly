package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Nidorino extends PokemonCard implements Evolvable {
    public Nidorino() {
        super("P033",
                "Nidorino",
                "Nidorino is a Poison type Pokémon introduced in Generation 1.",
                9,
                8,
                4);
    }

    @Override
    public PokemonCard evolve() {
        return new Nidoking();
    }
    //Create 10/29/68
}
