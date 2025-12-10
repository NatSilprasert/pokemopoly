package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Jigglypuff extends PokemonCard implements Evolvable {

    public Jigglypuff() {
        super("P039",
                "Jigglypuff",
                "Jigglypuff is a Normal type Pokémon introduced in Generation 1.",
                5,
                3,
                2,
                List.of(PokemonType.NORMAL));
    }

    @Override
    public PokemonCard evolve() {
        return new Wigglytuff();
    }
}
