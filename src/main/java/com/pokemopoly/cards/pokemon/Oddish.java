package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

public class Oddish extends PokemonCard implements Evolvable , BattleAbility {
    public Oddish(String id, String name, String description, int hp, int power, int price) {
        super("P043",
                "Oddish",
                "Oddish is a Grass/Poison type Pokémon introduced in Generation 1.",
                4,
                2,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Gloom();
    }

    @Override
    public void useBattlePassive(Battle battle) {
        //Waiting Battle Class
        //Growth
    }
    //Create 11/2/68
}
