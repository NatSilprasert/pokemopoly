package com.pokemopoly.player;

import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.cards.pokemon.Pikachu;
import com.pokemopoly.cards.pokemon.Magikarp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("Ash", ProfessionType.TRAINER); // Starts with Pikachu
    }

    @Test
    void testInitialSetup() {
        assertEquals("Ash", player.getName());
        assertEquals(ProfessionType.TRAINER, player.getProfession());

        // Trainer starts with Pikachu
        assertEquals(1, player.getTeam().size());
        assertTrue(player.getTeam().get(0) instanceof Pikachu);

        assertEquals(10, player.getCoin());
        assertEquals(5, player.getRedBall());
        assertEquals(0, player.getGreatBall());
        assertEquals(0, player.getHyperBall());
    }

    @Test
    void testAddPokemon() {
        PokemonCard m = new Magikarp();

        player.addPokemon(m);
        assertEquals(2, player.getTeam().size());
    }

    @Test
    void testTeamFull() {
        // Add until full
        for (int i = 0; i < 6; i++) {
            player.addPokemon(new Magikarp());
        }
        assertTrue(player.isTeamFull());

        // Try to add another
        player.addPokemon(new Pikachu());
        assertEquals(6, player.getTeam().size(), "Team must not exceed 6 Pokémon.");
    }

    @Test
    void testPositionWrap() {
        player.setPosition(38);
        player.setPosition(45); // 45 % 40 = 5
        assertEquals(5, player.getPosition());
    }

    @Test
    void testCoinSystem() {
        player.setCoin(50);
        assertEquals(50, player.getCoin());
    }

    @Test
    void testBadges() {
        assertFalse(player.getBadges1());
        assertFalse(player.getBadges2());

        player.setBadges1();
        player.setBadges2();

        assertTrue(player.getBadges1());
        assertTrue(player.getBadges2());
    }

    @Test
    void testGetAllCoin() {
        // Base values: 10 coins + Pikachu price
        int start = 10 + player.getTeam().get(0).getPrice();

        assertEquals(start, player.getAllCoin());

        // Add Magikarp
        PokemonCard m = new Magikarp();
        player.addPokemon(m);

        assertEquals(start + m.getPrice(), player.getAllCoin());

        // Add badges
        player.setBadges1();
        player.setBadges2();

        assertEquals(start + m.getPrice() + 14, player.getAllCoin(),
                "Badges should add +14 coins total.");
    }

    @Test
    void testPokeBallCounters() {
        player.setRedBall(3);
        player.setGreatBall(2);
        player.setHyperBall(1);

        assertEquals(3, player.getRedBall());
        assertEquals(2, player.getGreatBall());
        assertEquals(1, player.getHyperBall());
    }

    @Test
    void testFlags() {
        player.setSkipTurn(true);
        player.setDoNothing(true);

        assertTrue(player.isSkipTurn());
        assertTrue(player.isDoNothing());
    }
}