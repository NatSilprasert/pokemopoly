package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.CaptureAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.player.Player;

public class Diglett extends PokemonCard implements Evolvable , CaptureAbility {
    public Diglett() {
        super("P050",
                "Diglett",
                "Diglett is a Ground type Pokémon introduced in Generation 1.",
                5,
                4,
                2);
    }

    @Override
    public PokemonCard evolve() {
        return new Dugtrio();
    }

    @Override
    public void useCapturePassive(Player opponent) {
        System.out.println("🌋 Diglett uses Arena Trap!");

        int totalBalls = opponent.getRedBall() + opponent.getGreatBall() + opponent.getHyperBall();

        if (totalBalls == 0) {
            opponent.setSkipTurn(true);
            System.out.println("⛓ " + opponent.getName() + " is trapped by Diglett’s Arena Trap and cannot move next turn!");
        } else {
            System.out.println(opponent.getName() + " manages to escape because they have Poké Balls!");
        }
    }
    //Create 11/2/68
}
