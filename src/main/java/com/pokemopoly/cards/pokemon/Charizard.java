package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Charizard extends PokemonCard implements PreRollAbility {

    public Charizard() {
        super("P006","Charizard",
                "Charizard is a Fire/Flying type Pokémon introduced in Generation 1.",
                14,
                18,
                8);
    }

    @Override
    public void usePreRollPassive(Game game) {
        int roll = game.rollDice();
        System.out.println("Charizard used Flamethrower! Rolled a " + roll + "!");

        if (roll >= 3) {
            System.out.println("Flamethrower succeeded! Choose a player to burn one of their Pokémon.");

            List<Player> players = game.getPlayers();
            Player owner = getOwner();

            // Exclude the Charizard's owner
            List<Player> targets = new ArrayList<>();
            for (Player p : players) {
                if (p != owner) targets.add(p);
            }

            if (targets.isEmpty()) {
                System.out.println("No other players available to target.");
                return;
            }

            // Display available targets
            for (int i = 0; i < targets.size(); i++) {
                System.out.println((i + 1) + ". " + targets.get(i).getName());
            }

            Scanner scanner = new Scanner(System.in);
            Player targetPlayer = null;
            while (targetPlayer == null) {
                System.out.print("Select player to burn (1-" + targets.size() + "): ");
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= targets.size()) {
                    targetPlayer = targets.get(choice - 1);
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }

            // Select which Pokémon to burn
            if (targetPlayer.getTeam().isEmpty()) {
                System.out.println(targetPlayer.getName() + " has no Pokémon to burn!");
                return;
            }

            System.out.println("Choose a Pokémon from " + targetPlayer.getName() + "'s team:");
            for (int i = 0; i < targetPlayer.getTeam().size(); i++) {
                PokemonCard pc = targetPlayer.getTeam().get(i);
                System.out.println((i + 1) + ". " + pc.getName() +
                        (pc.isBurned() ? " (Already Burned)" : ""));
            }

            PokemonCard targetPokemon = null;
            while (targetPokemon == null) {
                System.out.print("Select Pokémon (1-" + targetPlayer.getTeam().size() + "): ");
                int choice = scanner.nextInt();
                if (choice >= 1 && choice <= targetPlayer.getTeam().size()) {
                    targetPokemon = targetPlayer.getTeam().get(choice - 1);
                } else {
                    System.out.println("Invalid choice. Try again.");
                }
            }

            // Apply burn
            if (targetPokemon.isBurned()) {
                System.out.println(targetPokemon.getName() + " is already burned!");
            } else {
                targetPokemon.setBurned(true);
                System.out.println("🔥 " + targetPokemon.getName() + " has been burned!");
            }

        } else {
            System.out.println("Flamethrower failed. No effect.");
        }
    }
    //Create 10/23/68
}
