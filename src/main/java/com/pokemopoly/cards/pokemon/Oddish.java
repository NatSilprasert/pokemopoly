package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Battle;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.List;

public class Oddish extends PokemonCard implements Evolvable , BattleAbility {
    public Oddish() {
        super("P043",
                "Oddish",
                "Oddish is a Grass/Poison type Pokémon introduced in Generation 1.",
                4,
                2,
                2,
                List.of(PokemonType.GRASS,PokemonType.POISON));
    }

    @Override
    public PokemonCard evolve() {
        return new Gloom();
    }

    @Override
    public void useBattlePassive(Battle battle) {
        System.out.println("🌱 Oddish uses Growth! Attack +2 this battle!");
        this.setPower(Math.max(this.getPower() + 2 , this.getMaxPower()));
    }
    //Create 11/2/68
}
