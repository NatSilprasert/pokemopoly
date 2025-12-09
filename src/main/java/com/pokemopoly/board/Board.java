package com.pokemopoly.board;

import com.pokemopoly.Game;
import com.pokemopoly.board.tile.StartTile;
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
        int oldPos = player.getPosition();
        int newPos = (oldPos + steps) % tiles.size();
        Tile tile = getTileAt(newPos);

        // ตัวแปรใหม่เก็บตำแหน่งสุดท้ายที่จะไป
        int finalPos = newPos;

        // เช็คว่าเดินผ่าน StartTile
        boolean passedStart = oldPos + steps > tiles.size();
        if (passedStart) {
            Tile startTile = getTileAt(0);
            if (startTile instanceof StartTile start) {
                // เรียก walkPass พร้อม callback
                start.walkPass(player, game, () -> {
                    // หลัง walkPass เสร็จ ถึงจะ set ตำแหน่งและเรียก onLand
                    player.setPosition(finalPos);

                    // ถ้า tile ปลายทางยังเป็น StartTile ให้เรียก onLand
                    if (tile instanceof StartTile st) {
                        st.onLand(player, game);
                    } else {
                        tile.moveIn(player, game);
                    }
                });
                return;
            }
        }

        // กรณีไม่ผ่าน StartTile หรือไม่เกิน 40
        player.setPosition(newPos);

        if (tile instanceof StartTile st) {
            st.onLand(player, game);
        } else {
            tile.moveIn(player, game);
        }
    }

    public int getSize() {
        return tiles.size();
    }

    public void blockTile(int currentPosition, int size) {
    }
}