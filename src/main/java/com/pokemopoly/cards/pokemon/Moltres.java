package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

import java.util.List;

public class Moltres extends PokemonCard {

    public Moltres() {
        super("P146",
                "Moltres",
                "Moltres is a Fire/Flying type Pokémon introduced in Generation 1.",
                20,
                18,
                10,
                List.of(PokemonType.FIRE,PokemonType.FLYING));
    }

//    @Override
//    public void useBattlePassive(Battle battle) {
//        PokemonCard enemy = battle.getOpponentPokemon(this);
//
//        if (enemy == null || !enemy.isAlive()) return;
//
//        // ถ้าอีกฝั่งไม่ถูก Burn อยู่แล้ว
//        if (!enemy.isBurned()) {
//            enemy.setBurned(true);
//            System.out.println("🔥 Heat Wave! " + enemy.getName() + " is burned!");
//        }
//    }
}