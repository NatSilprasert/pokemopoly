package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

import java.util.List;

public class Raticate extends PokemonCard {
    public Raticate() {
        super("P020",
                "Raticate",
                "Raticate is a Normal type Pokémon introduced in Generation 1.",
                8,
                10,
                4,
                List.of(PokemonType.NORMAL));
    }

//    @Override
//    public void useBattlePassive(Battle battle) {
//        PokemonCard opponent = battle.getOpponentPokemon(this);
//
//        if (opponent == null) return;
//
//        // ลด power ชั่วคราว
//        int originalPower = opponent.getPower();
//        opponent.setPower(originalPower - 1);
//
//        System.out.println("[Super Fang] " + opponent.getName() + " power -1 this battle.");
//    }
    //Create 10/29/68
}
