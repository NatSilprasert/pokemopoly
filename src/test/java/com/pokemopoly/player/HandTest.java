package com.pokemopoly.player;

import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.cards.items.Potion;
import com.pokemopoly.cards.items.Repel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    private Hand hand;

    @BeforeEach
    void setUp() {
        hand = new Hand(2); // capacity = 2
    }

    @Test
    void testInitialState() {
        assertEquals(2, hand.getCapacity());
        assertTrue(hand.getItems().isEmpty());
        assertFalse(hand.isFull());
    }

    @Test
    void testAddItem_Success() {
        ItemCard potion = new Potion();

        assertTrue(hand.add(potion));
        assertEquals(1, hand.getItems().size());
        assertEquals(potion, hand.getItems().get(0));
    }

    @Test
    void testAddItem_FailWhenFull() {
        hand.add(new Potion());
        hand.add(new Repel());

        assertTrue(hand.isFull());

        // Attempt to add third item
        ItemCard extra = new Potion();
        assertFalse(hand.add(extra)); // must fail
        assertEquals(2, hand.getItems().size());
    }

    @Test
    void testRemoveItem() {
        ItemCard potion = new Potion();
        ItemCard repel = new Repel();

        hand.add(potion);
        hand.add(repel);

        assertTrue(hand.remove(potion));
        assertEquals(1, hand.getItems().size());
        assertEquals(repel, hand.getItems().get(0));

        // Removing item not in hand
        assertFalse(hand.remove(potion));
    }

    @Test
    void testSetItem() {
        ItemCard potion = new Potion();
        ItemCard repel = new Repel();

        hand.add(potion);
        hand.add(repel);

        ItemCard newItem = new Potion();
        hand.setItem(1, newItem);

        assertEquals(newItem, hand.getItems().get(1));
        assertNotEquals(repel, hand.getItems().get(1));
    }

    @Test
    void testClearItems() {
        hand.add(new Potion());
        hand.add(new Repel());

        hand.clearItems();

        assertTrue(hand.getItems().isEmpty());
        assertFalse(hand.isFull());
    }

    @Test
    void testIsFull() {
        assertFalse(hand.isFull());

        hand.add(new Potion());
        hand.add(new Repel());

        assertTrue(hand.isFull());
    }
}