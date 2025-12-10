package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Nidorina extends PokemonCard implements Evolvable {
    public Nidorina() {
        super("P030",
                "Nidorina",
                "Nidorina is a Poison type Pokémon introduced in Generation 1.",
                10,
                6,
                4,
                List.of(PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Nidoqueen();
    }
}
