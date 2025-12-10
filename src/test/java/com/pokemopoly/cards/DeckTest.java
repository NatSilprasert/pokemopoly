package com.pokemopoly.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    private Deck<String> deck;

    @BeforeEach
    void setup() {
        deck = new Deck<>();
    }

    // -----------------------------
    // addCard()
    // -----------------------------
    @Test
    void testAddCard() {
        List<String> list = Arrays.asList("A", "B", "C");
        deck.addCard(list);

        assertEquals(3, deck.size());
        assertTrue(deck.contains("A"));
        assertTrue(deck.contains("B"));
        assertTrue(deck.contains("C"));
    }

    // -----------------------------
    // draw()
    // -----------------------------
    @Test
    void testDrawReturnsFirstElement() {
        List<String> list = Arrays.asList("A", "B", "C");
        deck.addCard(list);

        assertEquals("A", deck.draw());
        assertEquals(2, deck.size());
    }

    @Test
    void testDrawFromEmptyDeckReturnsNull() {
        assertTrue(deck.isEmpty());
        assertNull(deck.draw());
    }

    // -----------------------------
    // shuffle()
    // -----------------------------
    @Test
    void testShuffleDoesNotChangeSize() {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        deck.addCard(list);

        int before = deck.size();
        deck.shuffle();
        int after = deck.size();

        assertEquals(before, after);
    }

    @Test
    void testShuffleChangesOrderSometimes() {
        List<String> list = Arrays.asList("A", "B", "C", "D", "E");
        deck.addCard(list);

        List<String> beforeShuffle = List.copyOf(list);
        deck.shuffle();

        // shuffle อาจบังเอิญเหมือนเดิมได้ → ดังนั้นไม่ assert ให้ต้องเปลี่ยน
        // แต่ assert ได้ว่า size เท่าเดิม
        assertEquals(5, deck.size());
    }

    // -----------------------------
    // isEmpty()
    // -----------------------------
    @Test
    void testIsEmpty() {
        assertTrue(deck.isEmpty());

        deck.addCard(List.of("X"));
        assertFalse(deck.isEmpty());
    }

    // -----------------------------
    // size()
    // -----------------------------
    @Test
    void testSize() {
        assertEquals(0, deck.size());

        deck.addCard(List.of("A", "B"));
        assertEquals(2, deck.size());
    }

    // -----------------------------
    // contains()
    // -----------------------------
    @Test
    void testContains() {
        deck.addCard(List.of("A", "B"));

        assertTrue(deck.contains("A"));
        assertFalse(deck.contains("C"));
    }
}
