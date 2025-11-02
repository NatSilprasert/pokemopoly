package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

public class Raticate extends PokemonCard implements BattleAbility {
    public Raticate() {
        super("P020",
                "Raticate",
                "Raticate is a Normal type Pokémon introduced in Generation 1.",
                8,
                10,
                4);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //Wait Battle Class
        //Super Fang
    }
    //Create 10/29/68
}
