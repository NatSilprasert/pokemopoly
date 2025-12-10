package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Magikarp extends PokemonCard implements Evolvable {
    public Magikarp() {
        super("P129",
                "Magikarp",
                "Magikarp is a Water type Pokémon introduced in Generation 1.",
                3,
                1,
                1,
                List.of(PokemonType.WATER));
    }

    @Override
    public PokemonCard evolve() {
        return new Gyarados();
    }
}