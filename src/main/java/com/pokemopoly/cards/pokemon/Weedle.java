package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Weedle extends PokemonCard implements Evolvable {
    public Weedle() {
        super("P013",
                "Weedle",
                "Weedle is a Bug/Poison type Pokémon introduced in Generation 1.",
                3,
                3,
                2,
                List.of(PokemonType.BUG,PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Kakuna();
    }

}
