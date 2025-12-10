package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.List;

public class TrueRocket extends EventCard {
    public TrueRocket() {
        super("E-TR01", "True Rocket",
                "If you ask who we are... Losing 3 Coins");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();
        p.setCoin(Math.max(0, p.getCoin() - 3));

        System.out.println("[EVENT] True Rocket" + p.getName() + " lost 3 coins!");
    }
}