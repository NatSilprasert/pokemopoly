package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;

import java.net.PortUnreachableException;

public class Machamp extends PokemonCard implements PreRollAbility {
    public Machamp() {
        super("P068",
                "Machamp",
                "Machamp is a Fighting type Pokémon introduced in Generation 1.",
                13,
                16,
                8);
    }

    @Override
    public void usePreRollPassive(Game game) {
        //Close Combat
    }
}
