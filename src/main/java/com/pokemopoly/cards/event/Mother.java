package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;

import java.util.Scanner;

public class Mother extends EventCard {
    public Mother() {
        super("EVT_MOTHER", "Mother",
                "The player who draws this card can choose one of the following: 1. Draw 1 random item card 2. Heal 1 Pokémon in their team");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("👩 Mother Event Activated!");
        System.out.println(player.getName() + ", choose your reward:");
        System.out.println("1. Draw 1 random item card");
        System.out.println("2. Heal 1 Pokémon in your team");

        int choice;
        while (true) {
            System.out.print("Enter choice (1 or 2): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1 || choice == 2) break;
            }
            else scanner.next(); // consume invalid input

            System.out.println("❌ Invalid choice. Try again.");
        }

        if (choice == 1) {
            drawItemReward(game, player);
        }
        else if (choice == 2) {
            healPokemonReward(player, scanner);
        }
    }

    private void drawItemReward(Game game, Player player) {
        ItemCard item = game.getDeckManager().drawItem();
        Hand hand = player.getHand();

        System.out.println("🎁 You drew an item:");
        System.out.println(item.getName() + " - " + item.getDescription());

        if (hand.isFull()) {
            System.out.println("❌ Your hand is full!");
            System.out.println("1. Replace an existing item");
            System.out.println("2. Discard new item");

            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                System.out.println("Your items:");
                for (int i = 0; i < hand.getItems().size(); i++) {
                    ItemCard c = hand.getItems().get(i);
                    System.out.println((i + 1) + ". " + c.getName());
                }

                while (true) {
                    System.out.print("Select an item to replace: ");
                    int idx = scanner.nextInt() - 1;

                    if (idx >= 0 && idx < hand.getItems().size()) {
                        ItemCard removed = hand.getItems().remove(idx);
                        System.out.println("Removed: " + removed.getName());
                        hand.add(item);
                        System.out.println("Added: " + item.getName());
                        break;
                    }
                    System.out.println("❌ Invalid selection.");
                }
            } else {
                System.out.println("🗑 You discarded the item.");
                game.getDeckManager().getItemDeck().discard(item);
            }
        } else {
            hand.add(item);
            System.out.println("✨ The item has been added to your hand!");
        }
    }

    private void healPokemonReward(Player player, Scanner scanner) {
        if (player.getTeam().isEmpty()) {
            System.out.println("❌ You have no Pokémon to heal!");
            return;
        }

        System.out.println("❤️ Choose a Pokémon to heal:");

        for (int i = 0; i < player.getTeam().size(); i++) {
            PokemonCard p = player.getTeam().get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (HP: " + p.getHp() + "/" + p.getMaxHp() + ")");
        }

        int choice;
        while (true) {
            System.out.print("Enter Pokémon number: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= player.getTeam().size()) break;
            }
            else scanner.next();
            System.out.println("❌ Invalid selection.");
        }

        PokemonCard selected = player.getTeam().get(choice - 1);
        selected.setHp(selected.getMaxHp());
        System.out.println("✨ " + selected.getName() + " has been fully healed!");
    }
}
