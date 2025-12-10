package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Wartortle extends PokemonCard implements Evolvable {
    public Wartortle() {
        super("P008","Wartortle",
                "Wartortle is a Water type Pokémon introduced in Generation 1.",
                12,
                10,
                6,
                List.of(PokemonType.WATER));
    }

    @Override
    public PokemonCard evolve() {
        return new Blastoise();
    }
}
