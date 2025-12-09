package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

import java.util.List;

public class Zapdos extends PokemonCard {
    public Zapdos() {
        super("P145",
                "Zapdos",
                "Zapdos is an Electric/Flying type Pokémon introduced in Generation 1.",
                19,
                19,
                10,
                List.of(PokemonType.ELECTRIC,PokemonType.FLYING));
    }

//    @Override
//    public void useBattlePassive(Battle battle) {
//        PokemonCard enemy = battle.getOpponentPokemon(this);
//
//        if (enemy == null || !enemy.isAlive()) return;
//
//        // ถ้าอีกฝั่งไม่ถูก Burn อยู่แล้ว
//        if (!enemy.isParalyzed()) {
//            enemy.setParalyzed(true);
//            System.out.println("⚡ Static! " + enemy.getName() + " is paralyzed!");
//        } else {
//            System.out.println("⚡ Static triggered, but " + enemy.getName() + " is already paralyzed.");
//        }
//    }
}