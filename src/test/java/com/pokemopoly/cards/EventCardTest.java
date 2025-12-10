package com.pokemopoly.cards;

import com.pokemopoly.Game;
import com.pokemopoly.ui.MusicManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Dummy subclass เพื่อใช้ทดสอบ abstract EventCard
class DummyEventCard extends EventCard {

    boolean activated = false;

    public DummyEventCard() {
        super("EV-TEST", "Test Event", "This is a test event.");
    }

    @Override
    public void activate(Game game) {
        activated = true;
    }
}

public class EventCardTest {

    private DummyEventCard card;

    @BeforeEach
    void setUp() {
        card = new DummyEventCard();
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals("EV-TEST", card.getId());
        assertEquals("Test Event", card.getName());
        assertEquals("This is a test event.", card.getDescription());
    }

    @Test
    void testActivateMethod() {
        Game game = new Game(new MusicManager()); // หรือจะใช้ mock ก็ได้หาก Game ซับซ้อน

        assertFalse(card.activated, "Activated should start as false");

        card.activate(game);

        assertTrue(card.activated, "Activated should be true after activate() is called");
    }
}
