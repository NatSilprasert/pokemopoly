package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;

public class Chansey extends PokemonCard implements PreRollAbility {
    public Chansey() {
        super("P113",
                "Chansey",
                "Chansey is a Normal type Pokémon introduced in Generation 1.",
                35,
                3,
                6);
    }

    @Override
    public void usePreRollPassive(Game game) {
        int healAmount = this.getMaxHp() / 2;
        int newHp = Math.min(this.getHp() + healAmount, this.getMaxHp());

        this.setHp(newHp);

        System.out.println("✨ Chansey used Heal Pulse! Restored "
                + healAmount + " HP (" + this.getHp() + "/" + this.getMaxHp() + ")");
    }
}