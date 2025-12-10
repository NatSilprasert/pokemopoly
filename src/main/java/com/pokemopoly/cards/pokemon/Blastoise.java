package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Blastoise extends PokemonCard {

    public Blastoise() {
        super("P009","Blastoise",
                "Blastoise is a Water type Pokémon introduced in Generation 1.",
                15,
                17,
                8,
                List.of(PokemonType.WATER));
    }
}
