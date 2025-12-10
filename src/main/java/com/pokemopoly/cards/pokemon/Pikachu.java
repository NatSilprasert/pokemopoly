package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Pikachu extends PokemonCard implements Evolvable {
    public Pikachu() {
        super("P025",
                "Pikachu",
                "Pikachu is an Electric type Pokémon introduced in Generation 1.",
                10,
                6,
                4,
                List.of(PokemonType.ELECTRIC));
    }

    @Override
    public PokemonCard evolve() {
        return new Raichu();
    }
}
