package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Oddish extends PokemonCard implements Evolvable{
    public Oddish() {
        super("P043",
                "Oddish",
                "Oddish is a Grass/Poison type Pokémon introduced in Generation 1.",
                4,
                2,
                2,
                List.of(PokemonType.GRASS,PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Gloom();
    }
}
