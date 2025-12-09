package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Potion extends ItemCard {
    public Potion() {
        super("potion", "Potion", "Restore 3 HP to one Pokémon in your team.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        List<PokemonCard> team = player.getTeam();

        if (team.isEmpty()) {
            System.out.println("You have no Pokémon to heal!");
            return;
        }

        System.out.println("Choose a Pokémon to heal (+3 HP):");
        for (int i = 0; i < team.size(); i++) {
            PokemonCard p = team.get(i);
            System.out.println((i + 1) + ". " + p.getName() +
                    " (HP: " + p.getHp() + "/" + p.getMaxHp() + ")");
        }

        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.print("Select (1-" + team.size() + "): ");
            choice = scanner.nextInt() - 1;

            if (choice >= 0 && choice < team.size()) break;
            System.out.println("Invalid selection. Try again.");
        }

        PokemonCard selected = team.get(choice);
        int oldHp = selected.getHp();
        int newHp = Math.min(selected.getHp() + 3, selected.getMaxHp()); // เติมเลือด
        selected.setHp(newHp);

        System.out.println("🧪 Potion used on " + selected.getName() + "!");
        System.out.println("HP: " + oldHp + " → " + newHp);
    }
}
