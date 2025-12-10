package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Venonat extends PokemonCard implements Evolvable {
    public Venonat() {
        super("P048",
                "Venonat",
                "Venonat is a Bug/Poison type Pokémon introduced in Generation 1.",
                5,
                3,
                2,
                List.of(PokemonType.BUG,PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Venomoth();
    }
}
