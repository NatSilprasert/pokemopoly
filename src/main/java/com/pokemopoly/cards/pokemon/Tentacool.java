package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Tentacool extends PokemonCard implements Evolvable {
    public Tentacool() {
        super("P072",
                "Tentacool",
                "Tentacool is a Water/Poison type Pokémon introduced in Generation 1.",
                5,
                3,
                2,
                List.of(PokemonType.WATER,PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Tentacruel();
    }
}