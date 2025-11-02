package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Pikachu extends PokemonCard implements Evolvable {
    public Pikachu() {
        super("P025",
                "Pikachu",
                "Pikachu is an Electric type Pokémon introduced in Generation 1.",
                10,
                6,
                4);
    }

    @Override
    public PokemonCard evolve() {
        return new Raichu();
    }
    //Create 10/29/68
}
