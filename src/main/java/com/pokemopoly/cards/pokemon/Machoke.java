package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Machoke extends PokemonCard implements Evolvable {
    public Machoke() {
        super("P067",
                "Machoke",
                "Machoke is a Fighting type Pokémon introduced in Generation 1.",
                10,
                8,
                4);
    }

    @Override
    public PokemonCard evolve() {
        return new Machamp();
    }
}
