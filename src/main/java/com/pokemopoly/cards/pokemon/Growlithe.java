package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Growlithe extends PokemonCard implements Evolvable {
    public Growlithe() {
        super("P058",
                "Growlithe",
                "Growlithe is a Fire type Pokémon introduced in Generation 1.",
                8,
                8,
                4);
    }

    @Override
    public PokemonCard evolve() {
        return new Arcanine();
    }
}
