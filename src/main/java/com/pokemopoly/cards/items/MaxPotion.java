package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.MainGameUI;

import java.util.List;

public class MaxPotion extends ItemCard {
    public MaxPotion() {
        super("maxpotion", "Max Potion", "+2 dmg to all pokemon in your team.");
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
            card.setPower(card.getPower() + 2);
        }
    }
}
