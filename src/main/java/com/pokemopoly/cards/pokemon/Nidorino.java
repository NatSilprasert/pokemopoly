package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Nidorino extends PokemonCard implements Evolvable {
    public Nidorino() {
        super("P033",
                "Nidorino",
                "Nidorino is a Poison type Pokémon introduced in Generation 1.",
                9,
                8,
                4,
                List.of(PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Nidoking();
    }
}
