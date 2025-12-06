package com.pokemopoly.cards.pokemon;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;

import java.util.Random;

public class Eevee extends PokemonCard implements Evolvable {
    public Eevee() {
        super("P133",
                "Eevee",
                "Eevee is a Normal type Pokémon introduced in Generation 1.",
                4,
                4,
                3);
    }

    @Override
    public PokemonCard evolve() {
        Random rand = new Random();
        int roll = rand.nextInt(3); // 0,1,2

        switch (roll) {
            case 0:
                System.out.println("✨ Eevee evolved into Vaporeon!");
                return new Vaporeon();
            case 1:
                System.out.println("✨ Eevee evolved into Jolteon!");
                return new Jolteon();
            default:
                System.out.println("✨ Eevee evolved into Flareon!");
                return new Flareon();
        }
    }
}
