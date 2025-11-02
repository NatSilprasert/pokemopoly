package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;

public class Clefairy extends PokemonCard implements Evolvable {
    public Clefairy() {
        super("P035",
                "Clefairy",
                "Clefairy is a Fairy type Pokémon introduced in Generation 1.",
                5,
                2,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Clefable();
    }
    //Create 10/29/68
}
