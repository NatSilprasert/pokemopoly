package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Machop extends PokemonCard implements Evolvable {
    public Machop() {
        super("P066",
                "Machop",
                "Machop is a Fighting type Pokémon introduced in Generation 1.",
                5,
                4,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Machoke();
    }
}
