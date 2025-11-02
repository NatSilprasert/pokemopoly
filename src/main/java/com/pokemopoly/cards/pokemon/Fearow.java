package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

public class Fearow extends PokemonCard implements PreRollAbility {
    public Fearow() {
        super("P022",
                "Fearow",
                "Fearow is a Normal/Flying type Pokémon introduced in Generation 1.",
                11,
                11,
                6);
    }

    @Override
    public void usePreRollPassive(Game game) {
        Player owner = getOwner();
        if (owner == null) {
            System.out.println("⚠️ This Fearow has no owner assigned!");
            return;
        }

        System.out.println("🌬️ " + owner.getName() + "'s Fearow used Tailwind!");
        System.out.println(owner.getName() + " will move +1 extra spaces this turn!");

        // Roll dice first to determine base move
        int roll = game.rollDice();
        int totalMove = roll + 1;

        System.out.println(owner.getName() + " rolled a " + roll + ", but Tailwind adds +1!");
        System.out.println("➡️ Total movement: " + totalMove + " spaces!");

        // Move player on board
        game.getBoard().movePlayer(owner, totalMove, game);
        owner.move(totalMove);
    } // Tailwind but +1 move
    //Create 10/29/68
}
