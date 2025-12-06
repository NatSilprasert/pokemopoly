package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Drowzee extends PokemonCard implements Evolvable {
    public Drowzee() {
        super("P096",
                "Drowzee",
                "Drowzee is a Psychic type Pokémon introduced in Generation 1.",
                5,
                5,
                4);
    }

    @Override
    public PokemonCard evolve() {
        return new Hypno();
    }
}
