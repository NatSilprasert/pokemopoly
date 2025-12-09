package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.Scanner;

public class Revive extends ItemCard {

    public Revive() {
        super("revive", "Revive",
                "Revives a fainted Pokémon to 50% HP.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        Scanner scanner = new Scanner(System.in);

        // Find fainted Pokémon
        System.out.println("🧪 Using Revive...");
        System.out.println("Checking your team...");

        // List all fainted Pokémon
        int count = 0;
        for (PokemonCard p : player.getTeam()) {
            if (!p.isAlive()) count++;
        }

        if (count == 0) {
            System.out.println("❌ No fainted Pokémon to revive!");
            return;
        }

        System.out.println("Choose a Pokémon to revive:");
        for (int i = 0; i < player.getTeam().size(); i++) {
            PokemonCard p = player.getTeam().get(i);
            if (!p.isAlive()) {
                System.out.println((i + 1) + ". " + p.getName() + " (Fainted)");
            }
        }

        int choice;
        while (true) {
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt() - 1;

            if (choice >= 0 && choice < player.getTeam().size()
                    && !player.getTeam().get(choice).isAlive()) {
                break;
            }
            System.out.println("Invalid choice. Select a fainted Pokémon only.");
        }

        PokemonCard selected = player.getTeam().get(choice);

        // Revive logic
        int reviveHP = (selected.getMaxHp() + 1) / 2; // 50% rounded up
        selected.setAlive(true);
        selected.setHp(reviveHP);

        System.out.println("✨ " + selected.getName()
                + " has been revived with " + reviveHP + " HP!");
    }
}