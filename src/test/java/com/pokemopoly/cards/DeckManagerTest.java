package com.pokemopoly.cards;

import com.pokemopoly.Game;
import com.pokemopoly.ui.MainGameUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeckManagerTest {

    // ใช้ deck จริงทั้งหมด
    private Deck<ItemCard> itemDeck;
    private Deck<EventCard> eventDeck;
    private Deck<PokemonCard> blueDeck;
    private Deck<PokemonCard> greenDeck;
    private Deck<PokemonCard> purpleDeck;
    private Deck<PokemonCard> redDeck;
    private Deck<PokemonCard> crownDeck;

    private DeckManager manager;

    // Concrete test cards
    static class TestItem extends ItemCard {
        public TestItem(String id, String name, String description) {
            super(id, name, description);
        }

        @Override
        public void activate(Game game, MainGameUI gameUI) {

        }
    }
    static class TestEvent extends EventCard {
        public TestEvent(String id, String name, String description) {
            super(id, name, description);
        }

        @Override
        public void activate(Game game) {

        }
    }
    static class TestMon extends PokemonCard {
        public TestMon(String id, String name, String description, int hp, int power, int price, List<PokemonType> types) {
            super(id, name, description, hp, power, price, types);
        }
    }

    @BeforeEach
    void setup() {
        itemDeck = new Deck<>();
        eventDeck = new Deck<>();
        blueDeck = new Deck<>();
        greenDeck = new Deck<>();
        purpleDeck = new Deck<>();
        redDeck = new Deck<>();
        crownDeck = new Deck<>();

        manager = new DeckManager(
                itemDeck, eventDeck,
                blueDeck, greenDeck, purpleDeck, redDeck, crownDeck
        );
    }

    // ----------------------------------------------------
    // draw tests
    // ----------------------------------------------------

    @Test
    void testDrawItem() {
        TestItem item = new TestItem("1","name","desc");
        itemDeck.addCard(List.of(item));

        assertEquals(item, manager.drawItem());
        assertTrue(itemDeck.isEmpty());
    }

    @Test
    void testDrawBluePokemon() {
        TestMon mon = new TestMon("1","name","desc",1,1,1, Collections.singletonList(PokemonType.BUG));
        blueDeck.addCard(List.of(mon));

        assertEquals(mon, manager.drawBluePokemon());
    }

    // ----------------------------------------------------
    // isEmpty tests
    // ----------------------------------------------------

    @Test
    void testIsBlueEmpty() {
        assertTrue(manager.isBlueEmpty());

        blueDeck.addCard(List.of(new TestMon("1","name","desc",1,1,1, Collections.singletonList(PokemonType.BUG))));
        assertFalse(manager.isBlueEmpty());
    }

    // ----------------------------------------------------
    // shuffleAll
    // ----------------------------------------------------

    @Test
    void testShuffleAllDoesNotCrash() {
        blueDeck.addCard(List.of(new TestMon("1","name","desc",1,1,1, Collections.singletonList(PokemonType.BUG))
                , new TestMon("1","name","desc",1,1,1, Collections.singletonList(PokemonType.BUG)), new TestMon("1","name","desc",1,1,1, Collections.singletonList(PokemonType.BUG))));


        // ไม่ต้องเช็คว่าลำดับเปลี่ยน เพราะสุ่ม
        assertDoesNotThrow(() -> manager.shuffleAll());
    }

    // ----------------------------------------------------
    // getDeckColorOf()
    // ----------------------------------------------------

    @Test
    void testGetDeckColorOfBlue() {
        TestMon mon = new TestMon("1","name","desc",1,1,1, Collections.singletonList(PokemonType.BUG));
        blueDeck.addCard(List.of(mon));

        assertEquals(PokemonDeckColor.BLUE, manager.getDeckColorOf(mon));
    }

    @Test
    void testGetDeckColorOfNone() {
        TestMon mon = new TestMon("1","name","desc",1,1,1, Collections.singletonList(PokemonType.BUG));

        assertNull(manager.getDeckColorOf(mon));
    }
}
