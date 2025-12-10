package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;

import java.util.List;
import java.util.Scanner;

public class Butterfree extends PokemonCard {
    public Butterfree() {
        super(
                "P012",
                "Butterfree",
                "Butterfree is a Bug/Flying type Pokémon introduced in Generation 1.",
                12,
                7,
                6,
                List.of(PokemonType.BUG,PokemonType.FLYING));
    }
}
