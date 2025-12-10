package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Meowth extends PokemonCard implements Evolvable {
    public Meowth() {
        super("P052",
                "Meowth",
                "Meowth is a Normal type Pokémon introduced in Generation 1.",
                8,
                5,
                4,
                List.of(PokemonType.NORMAL));
    }

    @Override
    public PokemonCard evolve() {
        return new Persian();
    }
}
