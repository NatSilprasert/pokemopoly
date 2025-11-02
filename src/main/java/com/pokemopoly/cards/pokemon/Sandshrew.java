package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Sandshrew extends PokemonCard implements Evolvable , BattleAbility {
    public Sandshrew() {
        super("P027",
                "Sandshrew",
                "Sandshrew is a Ground type Pokémon introduced in Generation 1.",
                5,
                4,
                2);
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //Waiting Battle Class
        //Sand Attack
    }

    @Override
    public PokemonCard evolve() {
        return new Sandslash();
    }
    //Create 10/29/68
}
