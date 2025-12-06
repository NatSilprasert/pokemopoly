package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

public class Ditto extends PokemonCard implements BattleAbility {

    public Ditto() {
        super("P132",
                "Ditto",
                "Ditto is a Normal type Pokémon introduced in Generation 1.",
                1,
                1,
                3);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        PokemonCard opponentPokemon = battle.getOpponentPokemon(this);
        setHp(opponentPokemon.getHp());
        setPower(opponentPokemon.getPower());
        System.out.println("Ditto is copy!");
    }
}