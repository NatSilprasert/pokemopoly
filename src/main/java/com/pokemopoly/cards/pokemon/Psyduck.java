package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Psyduck extends PokemonCard implements Evolvable , PreRollAbility {
    public Psyduck() {
        super("P054",
                "Psyduck",
                "Psyduck is a Water type Pokémon introduced in Generation 1.",
                6,
                3,
                2);
    }

    @Override
    public void usePreRollPassive(Game game) {
        System.out.println("\n🌀 Psyduck used Tail Whip!");

        int roll = game.rollDice();
        System.out.println("🎲 Rolled a " + roll + "!");

        // Check if roll is even
        if (roll % 2 == 0) {
            System.out.println("✅ It's an even number! Choose a player to discard 1 card.");

            List<Player> players = game.getPlayers();
            Scanner scanner = new Scanner(System.in);

            // Show list of players (excluding Psyduck's owner)
            Player owner = getOwner();
            int index = 1;
            for (Player p : players) {
                if (p != owner) {
                    System.out.println(index + ". " + p.getName());
                    index++;
                }
            }

            if (index == 1) {
                System.out.println("⚠️ No other players available to target.");
                return;
            }

            System.out.print("Select player number: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Find selected player (skip owner)
            index = 1;
            Player target = null;
            for (Player p : players) {
                if (p != owner) {
                    if (index == choice) {
                        target = p;
                        break;
                    }
                    index++;
                }
            }

            if (target == null) {
                System.out.println("❌ Invalid choice. No card discarded.");
                return;
            }

            Hand hand = target.getHand();
            if (hand.getItems().isEmpty()) {
                System.out.println(target.getName() + " has no cards to discard!");
                return;
            }

            // Show target's hand
            System.out.println(target.getName() + "'s hand:");
            for (int i = 0; i < hand.getItems().size(); i++) {
                System.out.println((i + 1) + ". " + hand.getItems().get(i).getName());
            }

            System.out.print("Choose card number to discard: ");
            int discardIndex = scanner.nextInt() - 1;

            if (discardIndex >= 0 && discardIndex < hand.getItems().size()) {
                var removed = hand.getItems().remove(discardIndex);
                System.out.println("🗑️ " + target.getName() + " discarded " + removed.getName() + "!");
            } else {
                System.out.println("❌ Invalid choice. No card discarded.");
            }

        } else {
            System.out.println("❎ It's an odd number! Tail Whip failed.");
        }
    }

    @Override
    public PokemonCard evolve() {
        return new Golduck();
    }
    //Create 11/2/68
}
