package com.pokemopoly.cards;

import com.pokemopoly.Game;
import com.pokemopoly.ui.MainGameUI;

public abstract class ItemCard {
    private final String id;
    private final String name;
    private final String description;

    public ItemCard(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public boolean isAsync() {
        return false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    abstract public void activate(Game game, MainGameUI gameUI);

}
