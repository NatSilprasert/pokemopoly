package com.pokemopoly.cards.items;

import com.pokemopoly.Game;
import com.pokemopoly.MusicManager;
import com.pokemopoly.board.Board;
import com.pokemopoly.board.tile.CityTile;
import com.pokemopoly.cards.ItemCard;
import com.pokemopoly.player.Player;

public class TrainPass extends ItemCard {
    public TrainPass() {
        super("trainpass", "Train Pass",
                "Teleport to the nearest City tile ahead.");
    }

    @Override
    public void activate(Game game) {
        Player player = game.getCurrentPlayer();
        Board board = game.getBoard();

        int currentPos = player.getPosition();
        int boardSize = board.getSize();

        Integer targetPos = null;

        // Search forward for next CityTile
        for (int i = 1; i < boardSize; i++) {
            int nextPos = (currentPos + i) % boardSize;

            if (board.getTileAt(nextPos) instanceof CityTile) {
                targetPos = nextPos;
                break;
            }
        }

        if (targetPos == null) {
            System.out.println("⚠ No city tile found! (Should not happen)");
            return;
        }

        System.out.println("🚆 Train Pass activated!");
        System.out.println(player.getName() + " travels to the nearest city at tile " + targetPos);

        // Move player directly
        player.setPosition(targetPos);

        // Trigger tile effect
        board.getTileAt(targetPos).onLand(player, game);
    }
}
