
package com.pokemopoly.cards;

import com.pokemopoly.player.Player;

import java.util.List;

public abstract class PokemonCard implements Card {
    private final String id;
    private final String name;
    private final String description;
    private final List<PokemonType> types;
    private Player owner;

    private int maxHp;
    private int maxPower;
    private final int price;
    private PokemonDeckColor deckColor;
    private int hp;
    private int power;

    private boolean burned = false;
    private boolean paralyzed = false;
    private boolean isAlive = true;

    public PokemonCard(String id, String name, String description, int hp, int power, int price, List<PokemonType> types) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.maxHp = hp;
        this.power = power;
        this.maxPower = power;
        this.price = price;
        this.types = types;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<PokemonType> getTypes() {
        return types;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public void setMaxPower(int maxPower) {
        this.maxPower = maxPower;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isBurned() {
        return burned;
    }

    public void setBurned(boolean burned) {
        this.burned = burned;
    }

    public void setHp(int hp) {
        this.hp = Math.max(0, hp);
        if (this.hp == 0) {
            this.isAlive = false;
            System.out.println(name + " has fainted!");
        }
    }

    public boolean isParalyzed() {
        return paralyzed;
    }

    public void setParalyzed(boolean paralyzed) {
        this.paralyzed = paralyzed;
        if (paralyzed) {
            System.out.println(name + " is paralyzed! It may not move next turn ⚡");
        } else {
            System.out.println(name + " is no longer paralyzed.");
        }
    }

    public PokemonDeckColor getDeckColor() {
        return deckColor;
    }

    public void setDeckColor(PokemonDeckColor deckColor) {
        this.deckColor = deckColor;
    }
}
