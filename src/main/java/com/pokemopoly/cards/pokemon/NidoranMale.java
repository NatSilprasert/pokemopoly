package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class NidoranMale extends PokemonCard implements Evolvable {
    public NidoranMale() {
        super("P032",
                "Nidoran♂ (male)",
                "Nidoran♂ is a Poison type Pokémon introduced in Generation 1.",
                4,
                4,
                2,
                List.of(PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Nidorino();
    }
}
