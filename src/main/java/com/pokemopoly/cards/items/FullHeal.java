package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.ui.MainGameUI;

import java.util.List;

public class FullHeal extends ItemCard {
    public FullHeal() {
        super("fullheal", "Full Heal",
                "Heal all alive Pokémon in your team.");
    }

    @Override
    public void activate(Game game, MainGameUI gameUI) {
        Player player = game.getCurrentPlayer();
        List<PokemonCard> team = player.getTeam();

        if (team.isEmpty()) {
            System.out.println("You have no Pokémon to heal!");
            return;
        }

        for (PokemonCard card : team) {
            if (card.isAlive()) card.setHp(card.getMaxHp());
        }
    }
}
