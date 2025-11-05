package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

public class Dugtrio extends PokemonCard implements PreRollAbility {
    public Dugtrio() {
        super("P051",
                "Dugtrio",
                "Dugtrio is a Ground type Pokémon introduced in Generation 1.",
                10,
                14,
                6);
    }

    @Override
    public void usePreRollPassive(Game game) {
        // Too hard to do Bulldoze
        // The space where the player is standing cannot be used until one full round has been completed.
    }
    //Create 11/2/68
}
