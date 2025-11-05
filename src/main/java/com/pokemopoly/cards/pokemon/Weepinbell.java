package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Weepinbell extends PokemonCard implements Evolvable {
    public Weepinbell() {
        super("P070",
                "Weepinbell",
                "Weepinbell is a Grass/Poison type Pokémon introduced in Generation 1.",
                8,
                8,
                4);
    }

    @Override
    public PokemonCard evolve() {
        return new Victreebel();
    }
}

