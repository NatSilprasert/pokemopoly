package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.player.Player;

import java.util.List;

public class FightGiovanni extends EventCard {
    public FightGiovanni() {
        super("EV-FIGHT-GIOVANNI", "Fight Giovanni",
                "All players roll 1 dice. If their roll is high enough, they gain coins. If not, they lose coins.");
    }

    @Override
    public void activate(Game game) {
        System.out.println("🔥 Fight Giovanni Event Activated!");

        List<Player> players = game.getPlayers();
        int playerCount = players.size();

        int threshold;

        // Determine threshold based on number of players
        switch (playerCount) {
            case 2 -> threshold = 6;
            case 3 -> threshold = 9;
            case 4 -> threshold = 12;
            default -> {
                System.out.println("⚠ Invalid player count for this event!");
                return;
            }
        }

        System.out.println("Players must roll higher than " + threshold + " to win!");

        int total = 0;

        for (Player p : players) {
            int roll = game.rollDice();
            System.out.println("🎲 " + p.getName() + " rolled: " + roll);
            total += roll;
            System.out.println("total :");
        }
        for (Player p : players) {
            if (total > threshold) {
                p.setCoin(p.getCoin() + 5);
                System.out.println("✅ " + p.getName() + " succeeded and earns +5 coins!");
            } else {
                p.setCoin(p.getCoin() - 5);
                System.out.println("❌ " + p.getName() + " failed and loses -5 coins.");
            }
        }
    }
}
