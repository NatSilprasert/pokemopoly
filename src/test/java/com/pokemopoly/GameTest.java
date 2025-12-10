package com.pokemopoly;

import com.pokemopoly.board.Board;
import com.pokemopoly.board.tile.StartTile;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;
import com.pokemopoly.player.ProfessionType;
import com.pokemopoly.ui.MusicManager;
import javafx.embed.swing.JFXPanel;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    // Dummy music manager for testing
    private final MusicManager musicManager = new MusicManager();

    @BeforeEach
    void setUp() {
        // Initializes JavaFX runtime for testing
        new JFXPanel();

        game = new Game(musicManager);
        StackPane root = new StackPane();
        Runnable callback = () -> {};

        // Mock players
        Player p1 = new Player("Ash", ProfessionType.TRAINER);
        Player p2 = new Player("Misty", ProfessionType.FISHER);

        game.addPlayer(p1);
        game.addPlayer(p2);
        game.setPlayerCount(2);

        game.setUpGame(root, callback);
    }

    @Test
    void testBoardSetup() {
        Board board = game.getBoard();
        assertNotNull(board);
        assertEquals(40, board.getSize(), "Board must contain 40 tiles.");

        assertTrue(board.getTileAt(0) instanceof StartTile, "Tile 0 should be StartTile.");
    }

    @Test
    void testDeckSetup() {
        assertNotNull(game.getDeckManager());
        assertFalse(game.getDeckManager().drawItem() == null, "Item deck should not be empty.");
        assertFalse(game.getDeckManager().drawEvent() == null, "Event deck should not be empty.");
    }

    @Test
    void testPlayersAdded() {
        List<Player> players = game.getPlayers();
        assertEquals(2, players.size());
        assertEquals("Ash", players.get(0).getName());
        assertEquals("Misty", players.get(1).getName());
    }

    @Test
    void testStartingItems() {
        for (Player p : game.getPlayers()) {
            assertEquals(2, p.getHand().getItems().size(),
                    "Each player must start with 2 item cards.");
        }
    }

    @Test
    void testTurnRotation() {
        Player first = game.getCurrentPlayer();
        assertEquals("Ash", first.getName());

        game.nextPlayer();
        Player second = game.getCurrentPlayer();
        assertEquals("Misty", second.getName());

        game.nextPlayer();
        Player againFirst = game.getCurrentPlayer();
        assertEquals("Ash", againFirst.getName(), "Turn must rotate back to player 1.");
    }

    @Test
    void testGameEndCondition() {
        // Game ends at >= 50 * playerCount
        game.setTurn(100);
        assertTrue(game.isGameEnd());

        game.setTurn(10);
        assertFalse(game.isGameEnd());
    }

}