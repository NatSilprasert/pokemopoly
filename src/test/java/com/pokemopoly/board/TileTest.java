package com.pokemopoly.board;

import com.pokemopoly.Game;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;
import com.pokemopoly.ui.MusicManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    private static class DummyTile extends Tile {
        boolean onLandCalled = false;
        Player passedPlayer = null;

        public DummyTile(String name, int index) {
            super(name, index);
        }

        @Override
        public void onLand(Player player, Game game) {
            onLandCalled = true;
            passedPlayer = player;
        }
    }

    DummyTile tile;
    Player player;
    Game game;

    @BeforeEach
    void setUp() {
        tile = new DummyTile("TestTile", 5);
        player = new Player("Ash", ProfessionType.TRAINER);
        game = new Game(new MusicManager());
    }

    @Test
    void testConstructorInitialization() {
        assertEquals(5, tile.getIndex());
        assertEquals("5 - TestTile", tile.getName());
        assertNotNull(tile.playersOnLand);
        assertEquals(0, tile.playersOnLand.size());
    }

    @Test
    void testGetName() {
        assertEquals("5 - TestTile", tile.getName());
    }

    @Test
    void testMoveInCallsOnLand() {
        tile.moveIn(player, game);

        assertTrue(tile.onLandCalled, "onLand() must be called when moveIn() is triggered");
        assertEquals(player, tile.passedPlayer);
    }

    @Test
    void testMoveInAddsPlayerToList() {
        tile.moveIn(player, game);

        assertEquals(1, tile.playersOnLand.size());
        assertEquals(player, tile.playersOnLand.get(0));
    }
}