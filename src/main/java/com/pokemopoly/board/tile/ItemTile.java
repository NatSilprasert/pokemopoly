package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;

import java.util.Scanner;

public class ItemTile extends Tile {

    public ItemTile(String name, int index) {
        super(name, index);
    }

    public void onLand(Player player, Game game) {
        System.out.println(player.getName() + " landed on " + name + "!");
        ItemCard itemCard = game.getDeckManager().drawItem();

        System.out.println(player.getName() + " has drawn an item!");
        System.out.println("Item Card Details: ");
        System.out.println("Name: " + itemCard.getName());
        System.out.println("Description: " + itemCard.getDescription());

        Hand hand = player.getHand();
        if (hand.isFull()) {
            System.out.println("Your hand is full!");
            System.out.println("Do you want to:");
            System.out.println("1. Replace an existing item");
            System.out.println("2. Discard the new card");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1) {
                System.out.println("Your current items:");
                for (int i = 0; i < hand.getItems().size(); i++) {
                    ItemCard c = hand.getItems().get(i);
                    System.out.println((i + 1) + ". " + c.getName() + " - " + c.getDescription());
                }

                while (true) {
                    System.out.print("Select the item to replace (1-" + hand.getItems().size() + "): ");
                    int index = scanner.nextInt() - 1;

                    if (index >= 0 && index < hand.getItems().size()) {
                        ItemCard removed = hand.getItems().remove(index);
                        System.out.println("Removed: " + removed.getName());
                        hand.add(itemCard);
                        System.out.println("Added: " + itemCard.getName());
                        break;
                    } else {
                        System.out.println("Invalid selection. Please try again!");
                    }
                }
            } else if (choice == 2) {
                System.out.println("You discarded the new item card: " + itemCard.getName());
                game.getDeckManager().getItemDeck().discard(itemCard);
            }

        } else {
            hand.add(itemCard);
            System.out.println(itemCard.getName() + " has been added to your hand!");
        }
    }
}
