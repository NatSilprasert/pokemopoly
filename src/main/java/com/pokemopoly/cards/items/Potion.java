package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.MainGameUI;

import java.util.List;

import static java.lang.Math.min;

public class Potion extends ItemCard {
    public Potion() {
        super("potion", "Potion", "Restore 3 HP to all alive Pokémon in your team.");
    }

    @Override
    public void activate(Game game, MainGameUI gameUI) {
        Player player = game.getCurrentPlayer();
        List<PokemonCard> team = player.getTeam();

        if (team.isEmpty()) {
            System.out.println("You have no Pokémon!");
            return;
        }

        for (PokemonCard card : team) {
            card.setPower(min(card.getHp() + 3, card.getMaxHp()));
        }
    }
}
