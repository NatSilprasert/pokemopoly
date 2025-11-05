package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;

public class Arcanine extends PokemonCard implements BattleAbility {
    public Arcanine() {
        super("P059",
                "Arcanine",
                "Arcanine is a Fire type Pokémon introduced in Generation 1.",
                14,
                15,
                8);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //intimidate
    }
}
