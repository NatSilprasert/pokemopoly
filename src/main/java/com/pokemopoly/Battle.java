package com.pokemopoly;

import com.pokemopoly.board.Tile;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Battle {
    private Game game;
    private Player player;
    private Player opponent;
    private PokemonCard playerPokemon;
    private PokemonCard opponentPokemon;
    private boolean bot;

    public Battle(Game game, Player p1, Player p2, boolean isBot) {
        this.game = game;
        this.player = p1;
        this.opponent = p2;
        this.bot = isBot;
    }

    public Player start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Battle started!");
        playerPokemon = selectPokemon(player, scanner);
        if (bot) {
            // 1 Player Battle
            opponentPokemon = opponent.getTeam().getFirst();
            while (true) {
                int playerRoll = game.rollDice();
                int opponentRoll = game.rollDice();

                if (playerRoll > opponentRoll) {
                    System.out.println(player.getName() + "'s attack " + opponent.getName() + "!");

                    int damage = playerPokemon.getPower();
                    // todo check for additional damage

                    opponentPokemon.setHp(opponentPokemon.getHp() - damage);
                    if (opponentPokemon.getHp() <= 0) {
                        System.out.println(player.getName() + " win!");
                        // todo rewards
                        break;
                    }
                } else if (opponentRoll > playerRoll) {
                    System.out.println(opponent.getName() + "'s attack " + player.getName() + "!");

                    int damage = opponentPokemon.getPower();
                    // todo check for additional damage

                    playerPokemon.setHp(playerPokemon.getHp() - damage);
                    if (playerPokemon.getHp() <= 0) {
                        System.out.println(opponent.getName() + " win!");
                        // todo rewards
                        break;
                    }
                }
            }
        }
        else {
            // 2 Players Battle
            opponentPokemon = selectPokemon(opponent, scanner);
            while (true) {
                int playerRoll = game.rollDice();
                int opponentRoll = game.rollDice();

                if (playerRoll > opponentRoll) {
                    System.out.println(player.getName() + "'s attack " + opponent.getName() + "!");

                    int damage = playerPokemon.getPower();
                    // todo check for additional damage

                    opponentPokemon.setHp(opponentPokemon.getHp() - damage);
                    if (opponentPokemon.getHp() <= 0) {
                        System.out.println(player.getName() + " win!");
                        // todo rewards
                        break;
                    }
                }
                else if (opponentRoll > playerRoll) {
                    System.out.println(opponent.getName() + "'s attack " + player.getName() + "!");

                    int damage = opponentPokemon.getPower();
                    // todo check for additional damage

                    playerPokemon.setHp(playerPokemon.getHp() - damage);
                    if (playerPokemon.getHp() <= 0) {
                        System.out.println(opponent.getName() + " win!");
                        // todo rewards
                        break;
                    }
                }
            }
        }
    }

    private PokemonCard selectPokemon(Player player, Scanner scanner) {
        System.out.println(player.getName() + ", choose your pokemon:");

        List<PokemonCard> playerPokemon = player.getTeam();
        for (int i = 0; i < playerPokemon.size(); i++) {
            System.out.println(i + 1 + ". " + playerPokemon.get(i).getName());
        }

        int choice = scanner.nextInt();
        scanner.nextLine();

        return playerPokemon.get(choice - 1);
    }

}
