package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Rattata extends PokemonCard implements Evolvable, BattleAbility {
    public Rattata() {
        super("P019",
                "Rattata",
                "Rattata is a Normal type Pokémon introduced in Generation 1.",
                4,
                4,
                2);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //Wait Battle Class
        //Quick Attack
    }

    @Override
    public PokemonCard evolve() {
        return new Raticate();
    }

    //Create 10/29/68
}
