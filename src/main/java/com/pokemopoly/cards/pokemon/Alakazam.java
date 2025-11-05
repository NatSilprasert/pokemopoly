package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;

public class Alakazam extends PokemonCard implements PreRollAbility {
    public Alakazam() {
        super("P065",
                "Alakazam",
                "Alakazam is a Psychic type Pokémon introduced in Generation 1.",
                10,
                17,
                8);
    }

    @Override
    public void usePreRollPassive(Game game) {
        //Magic Guard
    }
}
