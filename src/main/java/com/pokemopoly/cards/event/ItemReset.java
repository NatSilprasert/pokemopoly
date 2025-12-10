package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.player.Player;

public class ItemReset extends EventCard {
    public ItemReset() {
        super("EVENT_ITEM_RESET", "Item Reset!",
                "Clear all player's items");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();

        p.getHand().clearItems();

        System.out.println("[EVENT] Item Reset! " + p.getName() + " lose all items!");
    }
}