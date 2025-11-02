package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Ninetales extends PokemonCard implements PreRollAbility {
    public Ninetales() {
        super("P038",
                "Ninetales",
                "Ninetales is a Fire type Pokémon introduced in Generation 1.",
                12,
                12,
                6);
    }

    @Override
    public void usePreRollPassive(Game game) {
        Scanner scanner = new Scanner(System.in);
        int roll = game.rollDice();
        System.out.println("🎲 Ninetales used Will-O-Wisp! Rolled a " + roll + ".");

        if (roll < 3) {
            System.out.println("❌ The attack missed! No Pokémon was burned.");
            return;
        }

        System.out.println("🔥 The Will-O-Wisp hit! Choose a Pokémon to burn.");

        // List all available Pokémon from all players
        List<Player> players = game.getPlayers();
        int count = 1;
        for (Player p : players) {
            for (PokemonCard pokemon : p.getTeam()) {
                System.out.println(count + ". " + pokemon.getName() +
                        " (Owner: " + p.getName() + ") " +
                        (pokemon.isBurned() ? "[Already Burned]" : ""));
                count++;
            }
        }

        if (count == 1) {
            System.out.println("No Pokémon available to burn!");
            return;
        }

        System.out.print("Enter the number of the Pokémon to burn: ");
        int choice = scanner.nextInt();

        count = 1;
        for (Player p : players) {
            for (PokemonCard pokemon : p.getTeam()) {
                if (count == choice) {
                    if (pokemon.isBurned()) {
                        System.out.println(pokemon.getName() + " is already burned!");
                    } else {
                        pokemon.setBurned(true);
                        System.out.println("🔥 " + pokemon.getName() + " has been burned!");
                    }
                    return;
                }
                count++;
            }
        }

        System.out.println("⚠️ Invalid choice. No Pokémon was burned.");
    }
    //Create 10/29/68
}
