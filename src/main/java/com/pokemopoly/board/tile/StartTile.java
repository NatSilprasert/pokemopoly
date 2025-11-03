package com.pokemopoly.board.tile;

import com.pokemopoly.Game;
import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class StartTile extends Tile {

    public StartTile(String name, int index) {
        super(name, index);
    }

    public void onLand(Player player, Game game) {
        System.out.println(player.getName() + " landed on " + name + "!");
    }

    public void walkPass(Player player, Game game) {
        Scanner sc = new Scanner(System.in);
        List<PokemonCard> pokemons = player.getTeam();

        if (pokemons.isEmpty()) {
            System.out.println(player.getName() + ", you have no Pokémon to sell.");
            return;
        }

        System.out.println("Do you want to sell your Pokémon?");
        System.out.println("1) Yes");
        System.out.println("2) No");

        int choice = -1;
        while (choice != 1 && choice != 2) {
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            }
        }

        if (choice == 2) {
            System.out.println("You decided not to sell any Pokémon.");
            return;
        }

        System.out.println("Select a Pokémon to sell:");
        for (int i = 0; i < pokemons.size(); i++) {
            PokemonCard p = pokemons.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (Price: " + p.getPrice() + ")");
        }

        int sellIndex = -1;
        while (sellIndex < 1 || sellIndex > pokemons.size()) {
            System.out.print("Enter the number of the Pokémon you want to sell: ");
            try {
                sellIndex = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        PokemonCard sold = pokemons.remove(sellIndex - 1);
        player.setCoin(player.getCoin() + sold.getPrice());

        System.out.println("You sold " + sold.getName() + " for " + sold.getPrice() + " coins!");
        System.out.println("Your new balance: " + player.getCoin() + " coins.");
    }
}
