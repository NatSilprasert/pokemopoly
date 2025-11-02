package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Butterfree extends PokemonCard implements PreRollAbility {
    public Butterfree() {
        super(
                "P012",
                "Butterfree",
                "Butterfree is a Bug/Flying type Pokémon introduced in Generation 1.",
                12,
                7,
                6
        );
    }

    @Override
    public void usePreRollPassive(Game game) {
        int roll = game.rollDice();
        System.out.println("🦋 Butterfree used Sleep Powder! Rolled a " + roll + ".");

        // Only triggers if the roll is exactly 3
        if (roll == 3) {
            List<Player> players = game.getPlayers();
            Player owner = getOwner();

            // Filter out the owner — cannot target self
            List<Player> targets = players.stream()
                    .filter(p -> !p.equals(owner))
                    .toList();

            if (targets.isEmpty()) {
                System.out.println("No other players to target!");
                return;
            }

            System.out.println("Choose a player to put to sleep:");
            for (int i = 0; i < targets.size(); i++) {
                System.out.println((i + 1) + ". " + targets.get(i).getName());
            }

            Scanner scanner = new Scanner(System.in);
            int choice;
            while (true) {
                System.out.print("Enter choice (1-" + targets.size() + "): ");
                choice = scanner.nextInt() - 1;
                if (choice >= 0 && choice < targets.size()) break;
                System.out.println("Invalid choice. Try again.");
            }

            Player chosen = targets.get(choice);
            chosen.setSkipTurn(true); // ← Requires simple boolean in Player class
            System.out.println("💤 " + chosen.getName() + " has been put to sleep and will skip their next turn!");
        } else {
            System.out.println("Sleep Powder missed!");
        }
    }
    //Create 10/23/68
}
