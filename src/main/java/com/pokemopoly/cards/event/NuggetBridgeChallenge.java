package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.player.Player;

public class NuggetBridgeChallenge extends EventCard {
    public NuggetBridgeChallenge() {
        super("E-NUG01", "Nugget Bridge Challenge",
                "Roll 2 dice. If the total is 8 or more, gain 10 coins. Otherwise, lose 5 coins.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();

        System.out.println("🌉 Nugget Bridge Challenge!");
        System.out.println(player.getName() + " must roll 2 dice!");

        int roll1 = game.rollDice();
        int roll2 = game.rollDice();

        int total = roll1 + roll2;

        System.out.println("🎲 Roll 1: " + roll1);
        System.out.println("🎲 Roll 2: " + roll2);
        System.out.println("➡ Total = " + total);

        if (total >= 8) {
            System.out.println("⭐ Success! You earn 10 coins!");
            player.setCoin(player.getCoin() + 10);
        } else {
            System.out.println("❌ Failed! You lose 5 coins!");
            player.setCoin(player.getCoin() - 5);
        }
    }
}
