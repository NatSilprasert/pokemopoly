package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

import java.util.List;

public class Arcanine extends PokemonCard {
    public Arcanine() {
        super("P059",
                "Arcanine",
                "Arcanine is a Fire type Pokémon introduced in Generation 1.",
                14,
                15,
                8,
                List.of(PokemonType.FIRE));
    }

//    @Override
//    public void useBattlePassive(Battle battle) {
//        PokemonCard opponentPokemon = battle.getOpponentPokemon(this);
//        opponentPokemon.setPower(opponentPokemon.getPower() - 2);
//    }
}
