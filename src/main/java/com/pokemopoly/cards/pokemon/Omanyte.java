package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;

import java.util.List;

public class Omanyte extends PokemonCard implements Evolvable , PreRollAbility {
    public Omanyte() {
        super("P138",
                "Omanyte",
                "Omanyte is a Rock/Water type Pokémon introduced in Generation 1.",
                10,
                3,
                4,
                List.of(PokemonType.ROCK,PokemonType.WATER));
    }

    @Override
    public PokemonCard evolve() {
        return new Omastar();
    }

    @Override
    public void usePreRollPassive(Game game) {
        // ฟื้น HP +2 (ไม่เกิน MaxHP)
        int newHp = Math.min(getHp() + 2, getMaxHp());
        setHp(newHp);

        System.out.println("🛡 Omanyte uses Withdraw! +2 HP → ("
                + getHp() + "/" + getMaxHp() + ")");
    }
}