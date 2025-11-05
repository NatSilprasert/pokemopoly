package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

public class Golbat extends PokemonCard implements BattleAbility {
    public Golbat() {
        super("P042",
                "Golbat",
                "Golbat is a Poison/Flying type Pokémon introduced in Generation 1.",
                11,
                7,
                5);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //Wait Battle Class
        //Inner Focus
    }
    //Create 11/2/68
}
