package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Magnemite extends PokemonCard implements Evolvable {

    public Magnemite() {
        super("P081",
                "Magnemite",
                "Magnemite is an Electric type Pokémon introduced in Generation 1.",
                4,
                4,
                2,
                List.of(PokemonType.ELECTRIC));
    }

    @Override
    public PokemonCard evolve() {
        return new Magneton();
    }
}