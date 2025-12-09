package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

import java.util.List;

public class Persian extends PokemonCard {
    public Persian() {
        super("P053",
                "Persian",
                "Persian is a Normal type Pokémon introduced in Generation 1.",
                11,
                13,
                6,
                List.of(PokemonType.NORMAL));
    }

//    @Override
//    public void useBattlePassive(Battle battle) {
//        if (battle == null) return;
//
//        PokemonCard opponent = battle.getOpponentPokemon(this);
//
//        if (opponent != null && opponent.isAlive()) {
//            int oldPower = opponent.getPower();
//            int newPower = Math.max(0, oldPower - 1);
//            opponent.setPower(newPower);
//
//            System.out.println("😼 Persian used Fake Out! " + opponent.getName()
//                    + "'s power dropped from " + oldPower + " → " + newPower);
//        }
//    }
    //Create 11/2/68
}
