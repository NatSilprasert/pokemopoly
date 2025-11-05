package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.board.Board;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

import java.util.List;
import java.util.Scanner;

public class Golduck extends PokemonCard implements PreRollAbility {
    public Golduck() {
        super("P055",
                "Golduck",
                "Golduck is a Water type Pokémon introduced in Generation 1.",
                11,
                12,
                6);
    }

    @Override
    public void usePreRollPassive(Game game) {
        System.out.println("\n💫 Golduck used Psych Up!");

        List<Player> players = game.getPlayers();
        Player owner = getOwner();

        // Find the last player who rolled (excluding owner)
        Player lastPlayer = null;
        int lastRoll = -1;
        for (Player p : players) {
            if (p != owner && p.getLastRoll() > 0 && p.getLastRoll() > lastRoll) {
                lastRoll = p.getLastRoll();
                lastPlayer = p;
            }
        }

        if (lastPlayer == null || lastRoll <= 0) {
            System.out.println("⚠️ No previous player roll available. Psych Up failed.");
            return;
        }

        System.out.println("✨ Golduck copies " + lastPlayer.getName() + "'s last roll of " + lastRoll + "!");
        Board board = game.getBoard();
        board.movePlayer(owner, lastRoll, game);
        owner.move(lastRoll);
    }
    //Create 11/2/68
}
