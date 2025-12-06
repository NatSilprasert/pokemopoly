package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;

import java.util.Scanner;

public class HM02Fly extends ItemCard {
    public HM02Fly() {
        super("I999", "HM02 Fly",
                "Move to any tile up to 10 spaces ahead.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("✈️ You used HM02 Fly!");
        System.out.println("Choose how far you want to fly (1–10 spaces):");

        int distance;
        while (true) {
            System.out.print("Enter distance: ");
            distance = scanner.nextInt();

            if (distance >= 1 && distance <= 10) break;

            System.out.println("Invalid distance! Please choose 1 to 10.");
        }

        int newPosition = (player.getPosition() + distance) % 40;

        System.out.println(player.getName() + " flies " + distance +
                " spaces to tile " + newPosition + "!");

        // prevent normal dice roll this turn
        player.setDoNothing(true);

        // actual board movement (trigger tile effects)
        game.getBoard().movePlayer(player, distance, game);

        // update player's position
        player.setPosition(newPosition);
    }
}
