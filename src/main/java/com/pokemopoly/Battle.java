package com.pokemopoly;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Battle {
    private Game game;
    private Player player;
    private Player opponent;
    private PokemonCard playerPokemon;
    private PokemonCard opponentPokemon;
    private boolean bot;
    private int playerOriginalPower;
    private int opponentOriginalPower;

    public Battle(Game game, Player p1, Player p2, boolean isBot) {
        this.game = game;
        this.player = p1;
        this.opponent = p2;
        this.bot = isBot;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean isWin = false;

        System.out.println("Battle started!");
        playerPokemon = selectPokemon(player, scanner);

        if (bot) {
            // Player vs Bot
            opponentPokemon = opponent.getTeam().getFirst();

            if (playerPokemon instanceof BattleAbility ability1) {
                ability1.useBattlePassive(this);
            }
            if (opponentPokemon instanceof BattleAbility ability2) {
                ability2.useBattlePassive(this);
            }

            while (!isWin) {
                int playerRoll = game.rollDice();
                int opponentRoll = game.rollDice();

                if (playerRoll > opponentRoll) {
                    isWin = attack(opponent, player, opponentPokemon, playerPokemon);
                }
                else if (opponentRoll > playerRoll) {
                    isWin = attack(opponent, player, opponentPokemon, playerPokemon);
                }
            }
        }
        else {
            // Player vs Player
            opponentPokemon = selectPokemon(opponent, scanner);

            if (playerPokemon instanceof BattleAbility ability1) {
                ability1.useBattlePassive(this);
            }
            if (opponentPokemon instanceof BattleAbility ability2) {
                ability2.useBattlePassive(this);
            }

            while (!isWin) {
                int playerRoll = game.rollDice();
                int opponentRoll = game.rollDice();

                if (playerRoll > opponentRoll) {
                    isWin = attack(opponent, player, opponentPokemon, playerPokemon);
                }
                else if (opponentRoll > playerRoll) {
                    isWin = attack(opponent, player, opponentPokemon, playerPokemon);
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

    private void resetPokemonPowers() {
        playerPokemon.setPower(playerPokemon.getMaxPower());
        opponentPokemon.setPower(opponentPokemon.getMaxPower());
        System.out.println("🔁 Both Pokémon's power have been restored to their original values.");
    }

    public PokemonCard getOpponentPokemon(PokemonCard pokemon) {
        if (pokemon == playerPokemon) {
            return opponentPokemon;
        } else if (pokemon == opponentPokemon) {
            return playerPokemon;
        }
        return null;
    }

    public boolean attack(Player attacker, Player defender, PokemonCard attackerPokemon, PokemonCard defenderPokemon) {
        System.out.println(attacker.getName() + "'s attack " + defender.getName() + "!");

        int damage = attackerPokemon.getPower();

        // check for additional damage
        if (attacker.getProfession() == ProfessionType.TRAINER) {
            damage += 2;
        }

        defenderPokemon.setHp(defenderPokemon.getHp() - damage);
        if (defenderPokemon.getHp() <= 0) {
            System.out.println(attacker.getName() + " win!");

            // player vs bot rewards
            if (bot && attacker == player) {
                attacker.setCoin(attacker.getCoin() + 5);
                if (Objects.equals("Gym 1's Leader", defender.getName())) {
                    attacker.setBadges1();
                }
                else if (Objects.equals("Gym 2's Leader", defender.getName())) {
                    attacker.setBadges2();
                }
                else if (Objects.equals("Villain", defender.getName())) {
                    List<PokemonCard> evolvablePokemons = attacker.getTeam().stream()
                            .filter(p -> p instanceof Evolvable)
                            .toList();

                    if (!evolvablePokemons.isEmpty()) {
                        System.out.println("Choose pokemon to evolve!");
                        for (int i = 0; i < evolvablePokemons.size(); i++) {
                            System.out.println((i + 1) + ". " + evolvablePokemons.get(i).getName());
                        }
                    }

                    Scanner scanner = new Scanner(System.in);
                    int choice = -1;

                    while (true) {
                        if (scanner.hasNextInt()) {
                            choice = scanner.nextInt();
                            if (choice >= 1 && choice <= evolvablePokemons.size()) {
                                break;
                            } else {
                                System.out.println("❌ Invalid number. Please select between 1 and " + evolvablePokemons.size() + "!");
                            }
                        } else {
                            System.out.println("❌ Please enter a valid number!");
                            scanner.next();
                        }
                    }

                    PokemonCard selected = evolvablePokemons.get(choice - 1);
                    System.out.println("You chose " + selected.getName() + " to evolve!");

                    Evolvable evolvable = (Evolvable) selected;
                    PokemonCard evolvedPokemon = evolvable.evolve();

                    int index = attacker.getTeam().indexOf(selected);
                    if (index != -1) {
                        attacker.getTeam().set(index, evolvedPokemon);
                    }

                    System.out.println("It has evolved to " + selected.getName() + "!");
                }
            }
            // player vs player rewards
            else {
                attacker.setCoin(attacker.getCoin() + 3);
            }

            resetPokemonPowers();
            return true;
        }

        return false;
    }
}
