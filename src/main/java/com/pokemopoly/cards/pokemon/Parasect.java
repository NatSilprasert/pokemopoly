package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Parasect extends PokemonCard implements PreRollAbility {

    public Parasect() {
        super("P047",
                "Parasect",
                "Parasect is a Bug/Grass type Pokémon introduced in Generation 1.",
                10,
                8,
                4);
    }

    @Override
    public void usePreRollPassive(Game game) {
        System.out.println("\n🍄 " + " Parasect uses Sleep Powder!");

        int roll = game.rollDice();
        System.out.println("🎲 Rolled a " + roll + "!");

        // Check for odd number
        if (roll % 2 == 1) {
            System.out.println("✨ Success! You may choose one player's Pokémon to put to sleep.");

            List<Player> players = game.getPlayers();
            Scanner scanner = new Scanner(System.in);

            // Show player list
            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                System.out.println((i + 1) + ". " + p.getName());
            }

            System.out.print("Select a player to target: ");
            int targetIndex = scanner.nextInt() - 1;
            if (targetIndex < 0 || targetIndex >= players.size()) {
                System.out.println("❌ Invalid selection.");
                return;
            }

            Player targetPlayer = players.get(targetIndex);
            if (targetPlayer.getTeam().isEmpty()) {
                System.out.println("❌ " + targetPlayer.getName() + " has no Pokémon to target!");
                return;
            }

            // Show Pokémon list
            System.out.println(targetPlayer.getName() + "'s Pokémon:");
            for (int i = 0; i < targetPlayer.getTeam().size(); i++) {
                PokemonCard poke = targetPlayer.getTeam().get(i);
                System.out.println((i + 1) + ". " + poke.getName() + " (HP: " + poke.getHp() + ")");
            }

            System.out.print("Select which Pokémon to put to sleep: ");
            int pokeIndex = scanner.nextInt() - 1;
            if (pokeIndex < 0 || pokeIndex >= targetPlayer.getTeam().size()) {
                System.out.println("❌ Invalid Pokémon selection.");
                return;
            }

            // Apply sleep effect
            targetPlayer.setSkipTurn(true);
            System.out.println("💤 " + targetPlayer.getName() + "'s " +
                    targetPlayer.getTeam().get(pokeIndex).getName() + " has been put to sleep!");

        } else {
            System.out.println("😴 Failed! Sleep Power did not activate.");
        }
    }
    //Create 11/2/68
}
