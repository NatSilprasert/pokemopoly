package com.pokemopoly.cards;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.PokemonDeckColor;
import com.pokemopoly.cards.PokemonType;
import com.pokemopoly.player.Player;

import com.pokemopoly.player.ProfessionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonCardTest {

    private PokemonCard card;

    // Dummy subclass for testing the abstract class
    static class DummyPokemonCard extends PokemonCard {
        public DummyPokemonCard() {
            super("PK-001", "Pikachu", "Electric mouse",
                    60, 50, 100,
                    List.of(PokemonType.ELECTRIC));
        }
    }

    @BeforeEach
    void setup() {
        card = new DummyPokemonCard();
    }

    @Test
    void testInitialValues() {
        assertEquals("Pikachu", card.getName());
        assertEquals("Electric mouse", card.getDescription());
        assertEquals(60, card.getHp());
        assertEquals(60, card.getMaxHp());
        assertEquals(50, card.getPower());
        assertEquals(50, card.getMaxPower());
        assertEquals(100, card.getPrice());
        assertEquals(List.of(PokemonType.ELECTRIC), card.getTypes());
        assertTrue(card.isAlive());
    }

    @Test
    void testSetHp_Normal() {
        card.setHp(30);
        assertEquals(30, card.getHp());
        assertTrue(card.isAlive());
    }

    @Test
    void testSetHp_ToZero_FaintsCorrectly() {
        card.setHp(0);
        assertEquals(0, card.getHp());
        assertFalse(card.isAlive(), "Card should faint when HP reaches 0");
    }

    @Test
    void testSetHp_Negative_HpCannotBeBelowZero() {
        card.setHp(-20);
        assertEquals(0, card.getHp());
        assertFalse(card.isAlive());
    }

    @Test
    void testSetPower() {
        card.setPower(80);
        assertEquals(80, card.getPower());
    }

    @Test
    void testSetMaxPower() {
        card.setMaxPower(120);
        assertEquals(120, card.getMaxPower());
    }

    @Test
    void testSetMaxHp() {
        card.setMaxHp(200);
        assertEquals(200, card.getMaxHp());
    }

    @Test
    void testDeckColorSetGet() {
        card.setDeckColor(PokemonDeckColor.BLUE);
        assertEquals(PokemonDeckColor.BLUE, card.getDeckColor());
    }

    @Test
    void testSetOwner() {
        Player p = new Player("Ash", ProfessionType.TRAINER);
        card.setOwner(p);
        // ไม่มี getter owner → test ได้แค่ไม่ throw error
        assertDoesNotThrow(() -> card.setOwner(p));
    }

    @Test
    void testSetAliveFlag() {
        card.setAlive(false);
        assertFalse(card.isAlive());
    }
}
