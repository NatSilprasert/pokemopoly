package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Snowball extends ItemCard {

    public Snowball() {
        super("snowball",
                "Snowball",
                "Choose 1 Pokémon. That Pokémon loses 2 HP.");
    }

    @Override
    public void activate(Game game) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose 1 Pokémon. That Pokémon loses 2 HP.");

        // รวมโปเกม่อนทุกตัวในเกมเป็นเป้าหมายได้
        List<Player> players = game.getPlayers();

        // แสดงตัวเลือก
        int index = 1;
        for (Player p : players) {
            for (PokemonCard pc : p.getTeam()) {
                System.out.println(index + ". " + pc.getName() +
                        " (Owner: " + p.getName() + ", HP: " + pc.getHp() + ")");
                index++;
            }
        }

        int choice;
        while (true) {
            System.out.print("Choose Number: ");
            choice = scanner.nextInt();
            if (choice >= 1 && choice < index) break;
            System.out.println("Invalid choice. Try again.");
        }

        // หาโปเกม่อนตามตัวเลือก
        int counter = 1;
        PokemonCard target = null;

        for (Player p : players) {
            for (PokemonCard pc : p.getTeam()) {
                if (counter == choice) {
                    target = pc;
                    break;
                }
                counter++;
            }
            if (target != null) break;
        }

        // ใช้ผล Snowball
        if (target != null) {
            System.out.println("❄ Snowball hits  + target.getName() + ! HP -2");
            target.setHp(target.getHp() - 2);
        }

        // ทิ้งการ์ดหลังใช้
        game.getCurrentPlayer().getHand().remove(this);
        game.getDeckManager().getItemDeck().discard(this);
    }
}