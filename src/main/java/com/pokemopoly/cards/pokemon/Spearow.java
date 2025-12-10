package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Spearow extends PokemonCard implements Evolvable {
    public Spearow() {
        super("P021",
                "Spearow",
                "Spearow is a Normal/Flying type Pokémon introduced in Generation 1.",
                5,
                3,
                2,
                List.of(PokemonType.NORMAL,PokemonType.FLYING));
    }

    @Override
    public PokemonCard evolve() {
        return null;
    }
}
