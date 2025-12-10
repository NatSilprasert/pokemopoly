package com.pokemopoly.board;

import com.pokemopoly.Game;
import com.pokemopoly.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test for Board class
 */
public class BoardTest {

    private Board board;
    private FakeGame game;
    private FakePlayer player;

    private FakeTile tile1;
    private FakeTile tile2;
    private FakeStartTile startTile;

    @BeforeEach
    void setup() {
        startTile = new FakeStartTile("Start");
        tile1 = new FakeTile("Tile1");
        tile2 = new FakeTile("Tile2");

        List<Tile> tiles = new ArrayList<>(List.of(startTile, tile1, tile2));
        board = new Board(tiles);

        game = new FakeGame();
        player = new FakePlayer("Ash");
    }

    @Test
    void testGetTileAtNormal() {
        assertEquals(startTile, board.getTileAt(0));
        assertEquals(tile1, board.getTileAt(1));
        assertEquals(tile2, board.getTileAt(2));
    }

    @Test
    void testGetTileAtWrapAround() {
        assertEquals(startTile, board.getTileAt(3));
        assertEquals(tile1, board.getTileAt(4));
        assertEquals(tile2, board.getTileAt(5));
    }

    @Test
    void testMovePlayer_NoPassStart() {
        player.setPosition(0);   // starts on startTile
        board.movePlayer(player, 1, game);

        assertEquals(1, player.getPosition());
        assertTrue(tile1.movedIn);
        assertFalse(startTile.walkPassed);
    }

    @Test
    void testMovePlayer_LandOnStart() {
        player.setPosition(1); // tile1
        board.movePlayer(player, 2, game); // 1 → 2 → 0

        assertEquals(0, player.getPosition());
        assertTrue(startTile.landed);
    }

    @Test
    void testMovePlayer_PassStart() {
        player.setPosition(2); // tile2 index = 2
        board.movePlayer(player, 2, game); // 2 + 2 = 4 → pass start

        assertEquals(1, player.getPosition()); // finalPos
        assertTrue(tile1.movedIn, "Landing tile should call moveIn()");
    }


    // -----------------------------
    // Fake classes for testing
    // -----------------------------

    private static class FakeTile extends Tile {
        boolean movedIn = false;

        public FakeTile(String name) { super(name, 0); }

        @Override
        public void moveIn(Player player, Game game) {
            movedIn = true;
        }

        @Override
        public void onLand(Player player, Game game) { }
    }

    private static class FakeStartTile extends Tile {

        boolean walkPassed = false;
        boolean landed = false;

        public FakeStartTile(String name) {
            super(name, 0);
        }

        @Override
        public void onLand(Player player, Game game) {
            landed = true;
        }
    }

    private static class FakePlayer extends Player {
        private int pos = 0;

        public FakePlayer(String name) { super(name, null); }

        @Override
        public int getPosition() { return pos; }

        @Override
        public void setPosition(int p) { pos = p; }
    }

    private static class FakeGame extends Game {
        public FakeGame() { super(null); }
    }
}