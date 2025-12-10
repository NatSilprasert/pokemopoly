package com.pokemopoly.board;

import com.pokemopoly.Battle;
import com.pokemopoly.Game;
import com.pokemopoly.MusicManager;
import com.pokemopoly.cards.PokemonCard;
import com.pokemopoly.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Tile {
    protected String name;
    protected int index;
    protected List<Player> playersOnLand;

    public Tile(String name, int index) {
        this.name = name;
        this.index = index;
        this.playersOnLand = new ArrayList<Player>();
    }

    public String getName() {
        return index + " - " + name;
    }

    public int getIndex() {
        return index;
    }

    public abstract void onLand(Player player, Game game);

    public void moveIn(Player player, Game game) {
        onLand(player, game);
        playersOnLand.add(player);
    }

    public void moveOut(Player player) {
        playersOnLand.remove(player);
    };
}
