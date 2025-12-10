package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Mankey extends PokemonCard implements Evolvable {
    public Mankey() {
        super("P056",
                "Mankey",
                "Mankey is a Fighting type Pokémon introduced in Generation 1.",
                5,
                4,
                2,
                List.of(PokemonType.FIGHTING));
    }

    @Override
    public PokemonCard evolve() {
        return new Primeape();
    }
}
