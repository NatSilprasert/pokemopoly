package com.pokemopoly.board;

import com.pokemopoly.Game;
import com.pokemopoly.player.Player;

import java.util.List;

public class Board {
    private final List<Tile> tiles;

    public Board(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public Tile getTileAt(int index) {
        return tiles.get(index % tiles.size());
    }

    public void movePlayer(Player player, int steps, Game game) {
        int newPos = (player.getPosition() + steps) % tiles.size();
        player.setPosition(newPos);

        Tile tile = getTileAt(newPos);
        tile.moveIn(player, game);
    }

    public int getSize() {
        return tiles.size();
    }
}