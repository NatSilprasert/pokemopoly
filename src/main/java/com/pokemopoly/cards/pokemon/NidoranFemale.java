package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class NidoranFemale extends PokemonCard implements Evolvable {
    public NidoranFemale() {
        super("P029",
                "Nidoran♀",
                "Nidoran♀ is a Poison type Pokémon introduced in Generation 1.",
                5,
                3,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Nidorina();
    }
    //Create 10/29/68
}
