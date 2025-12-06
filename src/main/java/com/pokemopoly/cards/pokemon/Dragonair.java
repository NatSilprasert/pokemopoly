package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Dragonair extends PokemonCard implements Evolvable {
    public Dragonair() {
        super("P148",
                "Dragonair",
                "Dragonair is a Dragon type Pokémon introduced in Generation 1.",
                10,
                12,
                7);
    }

    @Override
    public PokemonCard evolve() {
        return new Dragonite();
    }
}