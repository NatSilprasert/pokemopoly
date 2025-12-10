package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.items.Bicycle;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;

import java.util.Scanner;

public class ItemReset extends EventCard {
    public ItemReset() {
        super("EVENT_ITEM_RESET", "Item Reset!",
                "Clear all player's items");
    }

    @Override
    public void activate(Game game) {
        Player p = game.getCurrentPlayer();

        p.getHand().clearItems();

        System.out.println("[EVENT] Item Reset!" + p.getName() + " lose all items!");
    }
}