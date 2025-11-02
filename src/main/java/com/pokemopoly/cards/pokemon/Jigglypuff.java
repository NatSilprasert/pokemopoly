package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Jigglypuff extends PokemonCard implements Evolvable , PreRollAbility {

    public Jigglypuff() {
        super("P039",
                "Jigglypuff",
                "Jigglypuff is a Normal/Fairy type Pokémon introduced in Generation 1.",
                5,
                3,
                2);
    }

    @Override
    public void usePreRollPassive(Game game) {
        System.out.println("✨ Jigglypuff used Sing! 🎶");

        int roll = game.rollDice();
        System.out.println("🎲 Dice rolled: " + roll);

        if (roll < 4) {
            System.out.println("Jigglypuff’s Sing missed! Nothing happens.");
            return;
        }

        // Choose 1 Pokémon to put to sleep
        Scanner scanner = new Scanner(System.in);
        List<Player> players = game.getPlayers();

        // Show all Pokémon that are alive and not already asleep
        System.out.println("Choose a Pokémon to put to sleep:");

        int index = 1;
        for (Player p : players) {
            for (PokemonCard pokemon : p.getTeam()) {
                if (pokemon.isAlive()) {
                    System.out.println(index + ". " + pokemon.getName() + " (Owner: " + p.getName() + ")");
                    index++;
                }
            }
        }

        if (index == 1) {
            System.out.println("No valid Pokémon found to target.");
            return;
        }

        System.out.print("Enter the number of the Pokémon to put to sleep: ");
        int choice = scanner.nextInt();

        index = 1;
        for (Player p : players) {
            for (PokemonCard pokemon : p.getTeam()) {
                if (pokemon.isAlive()) {
                    if (index == choice) {
                        System.out.println("💤 " + pokemon.getName() + " fell asleep!");
                        p.setSkipTurn(true);
                        return;
                    }
                    index++;
                }
            }
        }

        System.out.println("Invalid choice. Jigglypuff’s Sing failed!");
    }

    @Override
    public PokemonCard evolve() {
        return new Wigglytuff();
    }
    //Create 10/29/68
}
