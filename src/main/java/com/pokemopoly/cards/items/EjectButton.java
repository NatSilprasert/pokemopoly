package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class EjectButton extends ItemCard {
    public EjectButton() {
        super("eject_button", "Eject Button",
                "Choose 1 player to move backward 3 tiles.");
    }

    @Override
    public void activate(Game game) {
        Scanner scanner = new Scanner(System.in);
        List<Player> players = game.getPlayers();

        System.out.println("🔵 Eject Button Activated!");
        System.out.println("Choose a player to move backward 3 tiles:");

        // List all players
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            System.out.println((i + 1) + ". " + p.getName() +
                    " (Position: " + p.getPosition() + ")");
        }

        int choice = -1;
        while (choice < 1 || choice > players.size()) {
            System.out.print("Select player (1-" + players.size() + "): ");
            choice = scanner.nextInt();
        }

        Player target = players.get(choice - 1);

        System.out.println("➡️ " + target.getName() + " will move backward 3 tiles!");

        int newPos = target.getPosition() - 3;

        // Wrap-around logic if goes before tile 0
        if (newPos < 0) {
            newPos += 40; // board size
        }

        target.setPosition(newPos);
        System.out.println("⬅️ " + target.getName() +
                " is now at tile " + newPos + ".");
    }
}
