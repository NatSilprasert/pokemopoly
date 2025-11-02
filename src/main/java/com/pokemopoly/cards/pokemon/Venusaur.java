package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;

import java.util.Scanner;

public class Venusaur extends PokemonCard implements PreRollAbility {
    public Venusaur() {
        super(
                "P003",
                "Venusaur",
                "Venusaur is a Grass/Poison type Pokémon introduced in Generation 1.",
                16,
                15,
                8
        );
    }

    @Override
    public void usePreRollPassive(Game game) {
        System.out.println("🌿 Venusaur used Frenzy Plant!");
        System.out.println("All other players must discard 1 card from their hand!");

        Scanner scanner = new Scanner(System.in);

        for (var player : game.getPlayers()) {
            // Skip the owner of this Venusaur
            if (player.equals(this.getOwner())) continue;

            var hand = player.getHand();
            var items = hand.getItems();

            if (items.isEmpty()) {
                System.out.println(player.getName() + " has no items to discard.");
                continue;
            }

            System.out.println("\n" + player.getName() + ", choose one card to discard:");
            for (int i = 0; i < items.size(); i++) {
                var c = items.get(i);
                System.out.println((i + 1) + ". " + c.getName() + " - " + c.getDescription());
            }

            int choice = -1;
            while (choice < 1 || choice > items.size()) {
                System.out.print("Enter a number (1-" + items.size() + "): ");
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                } else {
                    scanner.next(); // consume invalid input
                }
            }

            var discarded = items.remove(choice - 1);
            System.out.println(player.getName() + " discarded " + discarded.getName() + "!");

            // Optional: move discarded card to item discard pile
            game.getDeckManager().getItemDeck().discard(discarded);
        }
    }
    //Create 10/23/68
}
