package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Vulpix extends PokemonCard implements Evolvable {
    public Vulpix() {
        super("P037",
                "Vulpix",
                "Vulpix is a Fire type Pokémon introduced in Generation 1.",
                5,
                4,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Ninetales();
    }
    //Create 10/29/68
}
