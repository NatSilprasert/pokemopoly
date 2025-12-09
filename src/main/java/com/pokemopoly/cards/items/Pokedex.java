package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;

import java.util.Scanner;

public class Pokedex extends ItemCard {
    public Pokedex() {
        super("pokedex", "Pokedex", "Look at all item cards in another player's hand.");
    }

    @Override
    public void activate(Game game) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("📘 Pokedex activated!");
        System.out.println("Choose a player to inspect their items:");

        // list all players
        for (int i = 0; i < game.getPlayers().size(); i++) {
            Player p = game.getPlayers().get(i);
            System.out.println((i + 1) + ". " + p.getName());
        }

        int choice;
        while (true) {
            System.out.print("Select player number: ");
            choice = scanner.nextInt() - 1;
            if (choice >= 0 && choice < game.getPlayers().size()) break;
            System.out.println("Invalid choice! Try again.");
        }

        Player target = game.getPlayers().get(choice);

        System.out.println("\n🔍 Viewing items in " + target.getName() + "'s hand:");

        if (target.getHand().getItems().isEmpty()) {
            System.out.println("(No items)");
            return;
        }

        target.getHand().getItems().forEach(item ->
                System.out.println("- " + item.getName() + " : " + item.getDescription())
        );

        System.out.println("📘 End of Pokedex view.");
    }
}
