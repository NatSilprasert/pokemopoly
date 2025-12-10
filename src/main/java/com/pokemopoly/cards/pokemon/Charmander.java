package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Charmander extends PokemonCard implements Evolvable {
    public Charmander() {
        super("P004","Charmander",
                "Charmander is a Fire type Pokémon introduced in Generation 1.",
                6,
                7,
                3,
                List.of(PokemonType.FIRE));
    }

    @Override
    public PokemonCard evolve() {
        return new Charmeleon();
    }
}
