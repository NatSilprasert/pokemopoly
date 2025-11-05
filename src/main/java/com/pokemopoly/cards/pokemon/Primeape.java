package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

public class Primeape extends PokemonCard implements BattleAbility {
    public Primeape() {
        super("P057",
                "Primeape",
                "Primeape is a Fighting type Pokémon introduced in Generation 1.",
                9,
                9,
                4);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //Revenge
    }
}
