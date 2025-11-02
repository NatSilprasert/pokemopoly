package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Raichu extends PokemonCard implements PreRollAbility {
    public Raichu() {
        super("P026",
                "Raichu",
                "Raichu is an Electric type Pokémon introduced in Generation 1.",
                12,
                12,
                6);
    }

    @Override
    public void usePreRollPassive(Game game) {
        System.out.println("⚡ Raichu used Thunderbolt!");

        int roll = game.rollDice();
        System.out.println("🎲 You rolled a " + roll + "!");

        if (roll < 3) {
            System.out.println("The Thunderbolt missed! Nothing happens.");
            return;
        }

        System.out.println("It's super effective! Choose 1 Pokémon to paralyze.");

        // Collect all Pokémon in the game (excluding fainted ones)
        List<Player> players = game.getPlayers();
        Scanner scanner = new Scanner(System.in);

        int index = 1;
        for (Player p : players) {
            for (PokemonCard pokemon : p.getTeam()) {
                if (pokemon.isAlive()) {
                    System.out.println(index + ". " + pokemon.getName() + " (Owner: " + p.getName() + ")");
                    index++;
                }
            }
        }

        if (index == 1) {
            System.out.println("No available Pokémon to paralyze!");
            return;
        }

        System.out.print("Select the Pokémon to paralyze: ");
        int choice = scanner.nextInt();

        index = 1;
        outer:
        for (Player p : players) {
            for (PokemonCard pokemon : p.getTeam()) {
                if (pokemon.isAlive()) {
                    if (index == choice) {
                        pokemon.setParalyzed(true);
                        break outer;
                    }
                    index++;
                }
            }
        }

        System.out.println("⚡ Thunderbolt complete!");
    }
    //Create 10/29/68
}
