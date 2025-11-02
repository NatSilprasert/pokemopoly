package com.pokemopoly.cards.pokemon;

import com.pokemopoly.Game;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.interfaces.PreRollAbility;
import com.pokemopoly.player.Player;

public class Pidgeot extends PokemonCard implements PreRollAbility {

    public Pidgeot() {
        super("P018",
                "Pidgeot",
                "Pidgeot is a Normal/Flying type Pokémon introduced in Generation 1.",
                14,
                14,
                8);
    } //Edit 10/29/68

    @Override
    public void usePreRollPassive(Game game) {
        Player owner = getOwner();
        if (owner == null) {
            System.out.println("⚠️ This Pidgeot has no owner assigned!");
            return;
        }

        System.out.println("🌬️ " + owner.getName() + "'s Pidgeot used Tailwind!");
        System.out.println(owner.getName() + " will move +2 extra spaces this turn!");

        // Roll dice first to determine base move
        int roll = game.rollDice();
        int totalMove = roll + 2;

        System.out.println(owner.getName() + " rolled a " + roll + ", but Tailwind adds +2!");
        System.out.println("➡️ Total movement: " + totalMove + " spaces!");

        // Move player on board
        game.getBoard().movePlayer(owner, totalMove, game);
        owner.move(totalMove);
    }
    //Create 10/24/2025
}
