package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Meowth extends PokemonCard implements Evolvable , BattleAbility {
    public Meowth() {
        super("P052",
                "Meowth",
                "Meowth is a Normal type Pokémon introduced in Generation 1.",
                8,
                5,
                4);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        if (battle == null) return;

        PokemonCard opponent = battle.getOpponentPokemon(this);

        if (opponent != null && opponent.isAlive()) {
            int oldPower = opponent.getPower();
            int newPower = Math.max(0, oldPower - 1);
            opponent.setPower(newPower);

            System.out.println("😼 Meowth used Fake Out! " + opponent.getName()
                    + "'s power dropped from " + oldPower + " → " + newPower);
        }
    }

    @Override
    public PokemonCard evolve() {
        return new Persian();
    }
    //Create 11/2/68
}
