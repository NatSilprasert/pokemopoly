package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class FullHeal extends ItemCard {
    public FullHeal() {
        super("fullheal", "Full Heal",
                "Cures all abnormal status conditions from one Pokémon in your team.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        List<PokemonCard> team = player.getTeam();

        if (team.isEmpty()) {
            System.out.println("You have no Pokémon to heal!");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a Pokémon to fully heal (remove all status conditions):");
        for (int i = 0; i < team.size(); i++) {
            PokemonCard p = team.get(i);
            System.out.println((i + 1) + ". " + p.getName() +
                    " (Burned: " + p.isBurned() + ", Paralyzed: " + p.isParalyzed() + ")");
        }

        int choice;
        while (true) {
            System.out.print("Enter your choice (1-" + team.size() + "): ");
            choice = scanner.nextInt() - 1;

            if (choice >= 0 && choice < team.size()) break;
            System.out.println("Invalid choice. Try again.");
        }

        PokemonCard target = team.get(choice);

        boolean hadCondition = target.isBurned() || target.isParalyzed();

        // Cure statuses
        target.setBurned(false);
        target.setParalyzed(false);

        if (hadCondition) {
            System.out.println("✨ " + target.getName() + " has been fully healed! All status conditions removed.");
        } else {
            System.out.println(target.getName() + " had no abnormal status.");
        }

        // Remove item from hand after use
        player.getHand().remove(this);
    }
}
