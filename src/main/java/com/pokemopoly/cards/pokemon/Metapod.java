package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Metapod extends PokemonCard implements Evolvable, BattleAbility {
    public Metapod() {
        super("P011",
                "Metapod",
                "Metapod is a Bug type Pokémon introduced in Generation 1.",
                10,
                2,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Butterfree(); //Edit 10/29/68
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //Waiting Battle Class
        //Harden
    }
    //Create 10/23/68
}
