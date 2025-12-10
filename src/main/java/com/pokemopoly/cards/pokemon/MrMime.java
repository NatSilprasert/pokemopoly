package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

import java.util.List;

public class MrMime extends PokemonCard{

    public MrMime() {
        super("P122",
                "Mr. Mime",
                "Mr. Mime is a Psychic type Pokémon introduced in Generation 1.",
                8,
                9,
                4,
                List.of(PokemonType.PSYCHIC));
    }

//    @Override
//    public void useBattlePassive(Battle battle) {
//        // Mimic : Copy opponent's attack power if it's higher
//        PokemonCard opponent = battle.getOpponentPokemon(this);
//
//        if (opponent == null) return;
//
//        int myPower = this.getPower();
//        int opponentPower = opponent.getPower();
//
//        if (opponentPower > myPower) {
//            System.out.println("✨ Mr. Mime uses Mimic! Copying opponent's attack power ("
//                    + opponentPower + ")!");
//
//            // ก็อปพลังโจมตี
//            this.setPower(Math.max(opponentPower,getPower()));
//        }
//    }
}