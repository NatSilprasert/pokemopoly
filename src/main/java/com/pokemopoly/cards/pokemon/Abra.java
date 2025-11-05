package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Abra extends PokemonCard implements Evolvable {


    public Abra() {
        super("P063",
                "Abra",
                "Abra is a Psychic type Pokémon introduced in Generation 1.",
                3,
                3,
                3);
    }

    @Override
    public PokemonCard evolve() {
        return new Kadabra();
    }
}
