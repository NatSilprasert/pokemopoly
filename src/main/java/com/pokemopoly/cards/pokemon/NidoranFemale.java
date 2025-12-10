package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class NidoranFemale extends PokemonCard implements Evolvable {
    public NidoranFemale() {
        super("P029",
                "Nidoran♀ (female)",
                "Nidoran♀ is a Poison type Pokémon introduced in Generation 1.",
                5,
                3,
                2,
                List.of(PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Nidorina();
    }
}
