
package com.pokemopoly;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.cards.TypeEffectiveness;
import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
import com.pokemopoly.player.Player;

public class Battle {

    private final Game game;
    private final Player player;
    private final Player opponent;

    private PokemonCard playerPokemon;
    private PokemonCard opponentPokemon;

    private final boolean isBot;

    public Battle(Game game, Player p1, Player p2, boolean bot) {
        this.game = game;
        this.player = p1;
        this.opponent = p2;
        this.isBot = bot;
    }

    // UI จะเลือก pokemon แล้วส่งเข้ามา
    public void setPlayerPokemon(PokemonCard card) {
        this.playerPokemon = card;
    }

    // UI ใช้เรียกตอนเริ่ม
    public void setupBotPokemon() {
        this.opponentPokemon = opponent.getTeam().getFirst();

        // passive ability
        if (playerPokemon instanceof BattleAbility ab1) ab1.useBattlePassive(this);
        if (opponentPokemon instanceof BattleAbility ab2) ab2.useBattlePassive(this);
    }

    public PokemonCard getPlayerPokemon() {
        return playerPokemon;
    }

    public PokemonCard getOpponentPokemon() {
        return opponentPokemon;
    }

    public int rollPlayerDice() {
        return game.rollDice();
    }

    public int rollOpponentDice() {
        return game.rollDice();
    }

    public boolean doAttackRound(int playerRoll, int botRoll) {
        if (playerRoll > botRoll) {
            return attack(player, opponent, playerPokemon, opponentPokemon);
        } else if (botRoll > playerRoll) {
            return attack(opponent, player, opponentPokemon, playerPokemon);
        }
        return false;
    }

    public boolean attack(Player attacker, Player defender, PokemonCard atkPoke, PokemonCard defPoke) {

        int damage = atkPoke.getPower();

        for (PokemonType atkType : atkPoke.getTypes()) {
            for (PokemonType defType : defPoke.getTypes()) {
                if (TypeEffectiveness.getSuperEffective(atkType).contains(defType)) damage += 2;
                if (TypeEffectiveness.getNotEffective(atkType).contains(defType)) damage -= 2;
            }
        }

        defPoke.setHp(defPoke.getHp() - damage);

        return defPoke.getHp() <= 0;
    }

    public static class RewardResult {
        private String badgeName;
        private boolean gotBadge;
        private int coins;

        public void setBadge(String badgeName, boolean gotBadge) {
            this.badgeName = badgeName;
            this.gotBadge = gotBadge;
        }

        public void setCoins(int coins) {
            this.coins = coins;
        }

        public String getBadgeName() { return badgeName; }
        public boolean gotBadge() { return gotBadge; }
        public int getCoins() { return coins; }
    }

    // ... constructor และฟังก์ชันอื่น ๆ

    // UI เรียกเมื่อชนะ bot
    public RewardResult giveBotRewards() {
        RewardResult reward = new RewardResult();

        if (opponent.getName().equals("Gym 1's Leader")) {
            if (!player.getBadges1()) {
                player.setBadges1();
                reward.setBadge("badge1", true);
            } else {
                player.setCoin(player.getCoin() + 10);
                reward.setCoins(10);
            }
        }
        else if (opponent.getName().equals("Gym 2's Leader")) {
            if (!player.getBadges2()) {
                player.setBadges2();
                reward.setBadge("badge2", true);
            } else {
                player.setCoin(player.getCoin() + 10);
                reward.setCoins(10);
            }
        }
        else if (opponent.getName().equals("Villain")) {
            player.setCoin(player.getCoin() + 20);
            reward.setCoins(20);
        }

        return reward;
    }

    public PokemonCard evolve(PokemonCard oldCard) {
        Evolvable e = (Evolvable) oldCard;
        PokemonCard evo = e.evolve();
        int idx = player.getTeam().indexOf(oldCard);
        player.getTeam().set(idx, evo);
        return evo;
    }

    public boolean isBotBattle() {
        return isBot;
    }
}

//package com.pokemopoly;
//
//import com.pokemopoly.cards.PokemonCard;
//import com.pokemopoly.cards.PokemonType;
//import com.pokemopoly.cards.TypeEffectiveness;
//import com.pokemopoly.cards.pokemon.interfaces.BattleAbility;
//import com.pokemopoly.cards.pokemon.interfaces.Evolvable;
//import com.pokemopoly.player.Player;
//import com.pokemopoly.player.ProfessionType;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class Battle {
//    private Game game;
//    private Player player;
//    private Player opponent;
//    private PokemonCard playerPokemon;
//    private PokemonCard opponentPokemon;
//    private boolean bot;
//    private int playerOriginalPower;
//    private int opponentOriginalPower;
//
//    public Battle(Game game, Player p1, Player p2, boolean isBot) {
//        this.game = game;
//        this.player = p1;
//        this.opponent = p2;
//        this.bot = isBot;
//    }
//
//    public void start() {
//        Scanner scanner = new Scanner(System.in);
//        boolean isWin = false;
//
//        System.out.println("Battle started!");
//        playerPokemon = selectPokemon(player, scanner);
//
//        if (bot) {
//            // Player vs Bot
//            opponentPokemon = opponent.getTeam().getFirst();
//
//            if (playerPokemon instanceof BattleAbility ability1) {
//                ability1.useBattlePassive(this);
//            }
//            if (opponentPokemon instanceof BattleAbility ability2) {
//                ability2.useBattlePassive(this);
//            }
//
//            while (!isWin) {
//                int playerRoll = game.rollDice();
//                int opponentRoll = game.rollDice();
//
//                if (playerRoll > opponentRoll) {
//                    isWin = attack(opponent, player, opponentPokemon, playerPokemon);
//                }
//                else if (opponentRoll > playerRoll) {
//                    isWin = attack(opponent, player, opponentPokemon, playerPokemon);
//                }
//            }
//        }
//        else {
//            // Player vs Player
//            opponentPokemon = selectPokemon(opponent, scanner);
//
//            if (playerPokemon instanceof BattleAbility ability1) {
//                ability1.useBattlePassive(this);
//            }
//            if (opponentPokemon instanceof BattleAbility ability2) {
//                ability2.useBattlePassive(this);
//            }
//
//            while (!isWin) {
//                int playerRoll = game.rollDice();
//                int opponentRoll = game.rollDice();
//
//                if (playerRoll > opponentRoll) {
//                    isWin = attack(opponent, player, opponentPokemon, playerPokemon);
//                }
//                else if (opponentRoll > playerRoll) {
//                    isWin = attack(opponent, player, opponentPokemon, playerPokemon);
//                }
//            }
//        }
//    }
//
//    private PokemonCard selectPokemon(Player player, Scanner scanner) {
//        System.out.println(player.getName() + ", choose your pokemon:");
//
//        List<PokemonCard> playerPokemon = player.getTeam();
//        for (int i = 0; i < playerPokemon.size(); i++) {
//            System.out.println(i + 1 + ". " + playerPokemon.get(i).getName());
//        }
//
//        int choice = scanner.nextInt();
//        scanner.nextLine();
//
//        return playerPokemon.get(choice - 1);
//    }
//
//    private void resetPokemonPowers() {
//        playerPokemon.setPower(playerPokemon.getMaxPower());
//        opponentPokemon.setPower(opponentPokemon.getMaxPower());
//        System.out.println("🔁 Both Pokémon's power have been restored to their original values.");
//    }
//
//    public PokemonCard getOpponentPokemon(PokemonCard pokemon) {
//        if (pokemon == playerPokemon) {
//            return opponentPokemon;
//        } else if (pokemon == opponentPokemon) {
//            return playerPokemon;
//        }
//        return null;
//    }
//
//    public boolean attack(Player attacker, Player defender, PokemonCard attackerPokemon, PokemonCard defenderPokemon) {
//        System.out.println(attacker.getName() + "'s attack " + defender.getName() + "!");
//
//        int damage = attackerPokemon.getPower();
//
//        // check types
//        for (PokemonType attackType : attackerPokemon.getTypes()) {
//            for (PokemonType defenderType : defenderPokemon.getTypes()) {
//                if (TypeEffectiveness.getSuperEffective(attackType).contains(defenderType)) damage += 2;
//                if (TypeEffectiveness.getNotEffective(attackType).contains(defenderType)) damage -= 2;
//            }
//        }
//
//        defenderPokemon.setHp(defenderPokemon.getHp() - damage);
//
//        if (defenderPokemon.getHp() <= 0) {
//            Scanner scanner = new Scanner(System.in);
//            System.out.println(attacker.getName() + " win!");
//
//            // player vs bot rewards
//            if (bot && attacker == player) {
//                attacker.setCoin(attacker.getCoin() + 5);
//                if (Objects.equals("Gym 1's Leader", defender.getName())) {
//                    attacker.setBadges1();
//                }
//                else if (Objects.equals("Gym 2's Leader", defender.getName())) {
//                    attacker.setBadges2();
//                }
//                else if (Objects.equals("Villain", defender.getName())) {
//                    List<PokemonCard> evolvablePokemons = attacker.getTeam().stream()
//                            .filter(p -> p instanceof Evolvable)
//                            .toList();
//
//                    if (!evolvablePokemons.isEmpty()) {
//                        System.out.println("Choose pokemon to evolve!");
//                        for (int i = 0; i < evolvablePokemons.size(); i++) {
//                            System.out.println((i + 1) + ". " + evolvablePokemons.get(i).getName());
//                        }
//                    }
//
//                    int choice = -1;
//
//                    while (true) {
//                        if (scanner.hasNextInt()) {
//                            choice = scanner.nextInt();
//                            if (choice >= 1 && choice <= evolvablePokemons.size()) {
//                                break;
//                            } else {
//                                System.out.println("❌ Invalid number. Please select between 1 and " + evolvablePokemons.size() + "!");
//                            }
//                        } else {
//                            System.out.println("❌ Please enter a valid number!");
//                            scanner.next();
//                        }
//                    }
//
//                    PokemonCard selected = evolvablePokemons.get(choice - 1);
//                    System.out.println("You chose " + selected.getName() + " to evolve!");
//
//                    Evolvable evolvable = (Evolvable) selected;
//                    PokemonCard evolvedPokemon = evolvable.evolve();
//
//                    int index = attacker.getTeam().indexOf(selected);
//                    if (index != -1) {
//                        attacker.getTeam().set(index, evolvedPokemon);
//                    }
//
//                    System.out.println("It has evolved to " + selected.getName() + "!");
//                }
//            }
//            // player vs player rewards
//            else {
//                attacker.setCoin(attacker.getCoin() + 3);
//
//                if (attacker.getProfession() == ProfessionType.ROCKET && !attacker.isTeamFull()) {
//                    System.out.println("Choose pokemon to steal!");
//                    for (int i = 0; i < defender.getTeam().size(); i++) {
//                        System.out.println(i+1 + ": " + defender.getTeam().get(i).getName());
//                    }
//
//                    int choice = scanner.nextInt();
//                    PokemonCard selected = defender.getTeam().get(choice - 1);
//
//                    attacker.addPokemon(selected);
//                    defender.removePokemon(selected);
//                    System.out.println("You chose " + selected.getName() + " to your team!");
//                }
//            }
//
//            resetPokemonPowers();
//            return true;
//        }
//
//        return false;
//    }
//}
