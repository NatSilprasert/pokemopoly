package com.pokemopoly.cards;

import com.pokemopoly.Game;
import com.pokemopoly.ui.MainGameUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemCardTest {

    static class TestItemCard extends ItemCard {
        boolean activated = false;

        public TestItemCard() {
            super("IT-TEST-01", "Test Item", "Test Description");
        }

        @Override
        public void activate(Game game, MainGameUI ui) {
            activated = true;
        }
    }

    private TestItemCard card;

    @BeforeEach
    void setup() {
        card = new TestItemCard();
    }

    @Test
    void testGetters() {
        assertEquals("IT-TEST-01", card.getId());
        assertEquals("Test Item", card.getName());
        assertEquals("Test Description", card.getDescription());
    }

    @Test
    void testIsAsyncDefault() {
        assertFalse(card.isAsync(), "Default isAsync() should return false");
    }

    @Test
    void testActivateMethod() {
        Game dummyGame = null;       // ยังไม่จำเป็นต้องใช้ของจริง
        MainGameUI dummyUI = null;

        card.activate(dummyGame, dummyUI);

        assertTrue(card.activated, "activate() should set activated = true");
    }
}
