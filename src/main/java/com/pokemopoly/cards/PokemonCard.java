package com.pokemopoly.cards;

import com.pokemopoly.player.Player;

public abstract class PokemonCard implements Card {
    private final String id;
    private final String name;
    private final String description;
    private final int maxHp;
    private final int maxPower;
    private final int price;
    private int hp;
    private int power;
    private boolean isAlive = true;
    private Player owner;
    private boolean burned = false;
    private boolean paralyzed = false; //Edited 10/29/68


    public PokemonCard(String id, String name, String description, int hp, int power, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.hp = hp;
        this.maxHp = hp;
        this.power = power;
        this.maxPower = power;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
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
    } //Edit 10/23/68

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
    } //Edited 10/29/68

    public void setPower(int newPower) {

    }
}
