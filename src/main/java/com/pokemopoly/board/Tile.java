package com.pokemopoly.board;

import com.pokemopoly.Battle;
import com.pokemopoly.Game;
import com.pokemopoly.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Tile {
    protected String name;
    protected int index;
    protected List<Player> playersOnLand;

    public Tile(String name, int index) {
        this.name = name;
        this.index = index;
        this.playersOnLand = new ArrayList<Player>();
    }

    public String getName() {
        return index + " - " + name;
    }

    public int getIndex() {
        return index;
    }

    public abstract void onLand(Player player, Game game);

    public void moveIn(Player player, Game game) {
        if (!playersOnLand.isEmpty()) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("You're on the same tile with other players!");
            System.out.println("Do you want to fight? 1) Yes 2) No");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.println("Choose you opponent.");
                for (int i = 0; i < playersOnLand.size(); i++) {
                    System.out.println(i + 1 + ". " + playersOnLand.get(i).getName());
                }

                int opponentIdx = scanner.nextInt();
                scanner.nextLine();

                Player opponent = playersOnLand.get(opponentIdx - 1);
                Battle battle = new Battle(game, player, opponent, false);

                return;
            }
        }
        onLand(player, game);
        playersOnLand.add(player);
    }

    public void moveOut(Player player) {
        playersOnLand.remove(player);
    };
}
