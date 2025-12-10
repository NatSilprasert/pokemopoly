package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Pidgeotto extends PokemonCard implements Evolvable {
    public Pidgeotto() {
        super("P017",
                "Pidgeotto",
                "Pidgeotto is a Normal/Flying type Pokémon introduced in Generation 1.",
                12,
                6,
                4,
                List.of(PokemonType.NORMAL,PokemonType.FLYING));
    }

    @Override
    public PokemonCard evolve() {
        return new Pidgeot();
    }
}
