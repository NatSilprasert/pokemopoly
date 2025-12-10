package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.MainGameUI;

import java.util.List;

public class Snowball extends ItemCard {

    public Snowball() {
        super("snowball",
                "Snowball",
                "Skip next player's turn.");
    }

    @Override
    public void activate(Game game, MainGameUI gameUI) {
        Player user = game.getCurrentPlayer();
        List<Player> players = game.getPlayers();
        int total = players.size();

        int currentIndex = players.indexOf(user);
        Player nextPlayer = players.get((currentIndex + 1) % total);
        nextPlayer.setSkipTurn(true);

        System.out.println("❄️ " + user.getName() + " used Snowball!");
        System.out.println("➡️ " + nextPlayer.getName() + " will skip their next turn!");
    }
}