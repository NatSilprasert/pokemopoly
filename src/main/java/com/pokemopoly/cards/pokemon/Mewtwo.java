
package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;

public class Mewtwo extends PokemonCard {
    public Mewtwo() {
        super("P151",
                "Mewtwo",
                "Mew is a Psychic type Pokémon introduced in Generation 1.",
                20,
                20,
                12,
                List.of(PokemonType.PSYCHIC));
    }
}
