package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Rattata extends PokemonCard implements Evolvable {
    public Rattata() {
        super("P019",
                "Rattata",
                "Rattata is a Normal type Pokémon introduced in Generation 1.",
                4,
                4,
                2,
                List.of(PokemonType.NORMAL));
    }

    @Override
    public PokemonCard evolve() {
        return new Raticate();
    }
}
