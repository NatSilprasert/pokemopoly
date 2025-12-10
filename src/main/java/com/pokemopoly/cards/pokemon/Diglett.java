package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Diglett extends PokemonCard implements Evolvable {
    public Diglett() {
        super("P050",
                "Diglett",
                "Diglett is a Ground type Pokémon introduced in Generation 1.",
                5,
                4,
                2,
                List.of(PokemonType.GROUND));
    }

    @Override
    public PokemonCard evolve() {
        return new Dugtrio();
    }
    
}
