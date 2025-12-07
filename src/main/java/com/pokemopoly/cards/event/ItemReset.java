package com.pokemopoly.cards.event;

import com.pokemopoly.Game;
import com.pokemopoly.cards.EventCard;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Hand;
import com.pokemopoly.player.Player;

import java.util.Scanner;

public class ItemReset extends EventCard {
    public ItemReset() {
        super("EVENT_ITEM_RESET", "Item Reset!",
                "You may discard all items in your hand and redraw the same amount.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        Hand hand = player.getHand();
        Scanner scanner = new Scanner(System.in);

        System.out.println("🔄 ITEM RESET EVENT!");
        System.out.println(player.getName() + ", you may discard ALL your item cards and redraw the same number.");
        System.out.println("Press 1 to discard and redraw.");
        System.out.println("Press 2 to do nothing.");

        int choice = scanner.nextInt();
        scanner.nextLine(); // clear newline

        if (choice != 1) {
            System.out.println(player.getName() + " chose not to use Item Reset.");
            return;
        }

        int count = hand.getItems().size();

        if (count == 0) {
            System.out.println("❌ You have no items to reset!");
            return;
        }

        System.out.println("You discarded " + count + " items.");

        // Discard all items to discard pile
        for (ItemCard c : hand.getItems()) {
            game.getDeckManager().getItemDeck().discard(c);
        }
        hand.getItems().clear();

        // Draw the same number of new items
        System.out.println("Drawing " + count + " new item(s)...");
        for (int i = 0; i < count; i++) {
            ItemCard newCard = game.getDeckManager().drawItem();
            hand.add(newCard);
            System.out.println(" + Drew: " + newCard.getName());
        }

        System.out.println("✔ Item Reset completed!");
    }
}
