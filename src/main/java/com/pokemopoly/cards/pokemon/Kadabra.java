package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Kadabra extends PokemonCard implements Evolvable {
    public Kadabra() {
        super("P063",
                "Kadabra",
                "Kadabra is a Psychic type Pokémon introduced in Generation 1.",
                8,
                12,
                6,
                List.of(PokemonType.PSYCHIC));
    }

    @Override
    public PokemonCard evolve() {
        return new Alakazam();
    }
}
