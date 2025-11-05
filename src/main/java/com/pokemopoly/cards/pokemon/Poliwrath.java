package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;

public class Poliwrath extends PokemonCard implements PreRollAbility {
    public Poliwrath() {
        super("P062",
                "Poliwrath",
                "Poliwrath is a Water/Fighting type Pokémon introduced in Generation 1.",
                12,
                12,
                6);
    }

    @Override
    public void usePreRollPassive(Game game) {
        //Dynamic Punch
        //1/6 Change to Double Attack
    }
}
